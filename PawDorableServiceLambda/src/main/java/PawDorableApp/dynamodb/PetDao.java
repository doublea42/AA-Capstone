package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.exceptions.PetInvalidValuesException;
import PawDorableApp.exceptions.PetNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import PawDorableApp.models.Gender;
import PawDorableApp.models.KindOfPet;
import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class PetDao {

    private final Logger log = LogManager.getLogger();
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public PetDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Pet getPet(String petID){
        Pet selectedPet = this.dynamoDbMapper.load(Pet.class ,petID);
        if(petID == null){
            metricsPublisher.addCount(MetricsConstants.GET_PET_PET_NOT_FOUND_COUNT, 1);
            throw new PetNotFoundException("could not find Pet with id");
        }

        metricsPublisher.addCount(MetricsConstants.GET_PET_PET_NOT_FOUND_COUNT, 0);
        return selectedPet;
    }

    public Pet saveNewPet(String kindOfPet, String name, String ownerEmail, String age,String gender,String available){

        KindOfPet kind = PawDorableServiceUtils.petEnum(kindOfPet);
        int petAge = Integer.parseInt(age);
        Gender petsGender = PawDorableServiceUtils.genderEnum(gender);
        Boolean isPetAvailable = this.isAvailableCheck(available);

//        log.info("isPetAvailable ----------> {} class -----> type {}  ", isPetAvailable, isPetAvailable.getClass());


        if(kind == null || name == null || name.isEmpty()
                || ownerEmail == null || ownerEmail.isEmpty()
                || petAge > 10 || petAge < 2
                || petsGender == null){


            log.info("PetDao values  ---- > kind: {} name: {}, ownerEmail: {}, age: {}, gender: {}",
                    kind, name, ownerEmail, petAge,petsGender);

            metricsPublisher.addCount(MetricsConstants.UPDATE_PET_INVALID_ATTRIBUTE_VALUE ,1);
            throw new PetInvalidValuesException("could not update pet with current values");

        }

        Pet selectedPet = new Pet();

        String newID = "PET_" + PawDorableServiceUtils.generateId();
        while(this.getPet(newID) != null){
            newID = PawDorableServiceUtils.generateId();
        }

        Set<String> rentalHistory = new HashSet<>();
        rentalHistory.add("0");

        selectedPet.setRentalHistory(rentalHistory);

        selectedPet.setID(newID);
        selectedPet.setKindOfPet(kind);
        selectedPet.setName(name);
        selectedPet.setOwnerEmail(ownerEmail);
        selectedPet.setAge(petAge);
        selectedPet.setGender(petsGender);
        selectedPet.setAvailable(isPetAvailable);

        dynamoDbMapper.save(selectedPet);
        return selectedPet;
    }


    public Pet updateAvailablePet(String petID, String available){

        Boolean isPetAvailable = this.isAvailableCheck(available);

        Pet selectedPet = this.getPet(petID);
        selectedPet.setAvailable(isPetAvailable);

        dynamoDbMapper.save(selectedPet);
        return selectedPet;
    }

    public void addRentalHistory (String petID, String rentalH) {
        Pet selectedPet = this.getPet(petID);

        Set<String> tempList = selectedPet.getRentalHistory();
        if(tempList.size() == 1){
            tempList.remove("0");
        }
        selectedPet.setRentalHistory(tempList);
        tempList.add(rentalH);

        dynamoDbMapper.save(selectedPet);
    }

    public Boolean removePet(String petID){

        Pet selectedPet = this.getPet(petID);
        dynamoDbMapper.delete(selectedPet);
        return this.getPet(petID) == null;
    }

    private Boolean isAvailableCheck(String check){
        if(check.toLowerCase().equals("true")){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
