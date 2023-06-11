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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public Pet savePet(boolean isNew, String ID, String kindOfPet, String name,
                       String ownerEmail, String age, String gender,
                       Set<String> rentalHistory, String available){

        KindOfPet kind = PawDorableServiceUtils.petEnum(kindOfPet);
        Gender petsGender = PawDorableServiceUtils.genderEnum(gender);
        boolean isPetAvailable = Boolean.parseBoolean(available);


//        log.info("here <---------{}---------{}------------------",kind, petsGender);


        if(kind == null || name == null || name.isEmpty()
               || ownerEmail == null || ownerEmail.isEmpty()
               || age == null || age.isEmpty()
               || petsGender == null){



           metricsPublisher.addCount(MetricsConstants.UPDATE_PET_INVALID_ATTRIBUTE_VALUE ,1);
           throw new PetInvalidValuesException("could not update pet with current values");

       }

        Pet selectedPet = new Pet();

       if(isNew){
           selectedPet.setID(PawDorableServiceUtils.generateId());
//           selectedPet.setRentalHistory(new HashSet<>());
       }
       else{
           Pet tempPet = this.getPet(ID);

           if(!rentalHistory.isEmpty()){
               if(tempPet.getRentalHistory() == null){
                   selectedPet.setRentalHistory(rentalHistory);
               }
               Set<String> history = tempPet.getRentalHistory();
               history.addAll(rentalHistory);
               selectedPet.setRentalHistory(history);
           }
           else{
               if(tempPet.getRentalHistory() != null){
                   selectedPet.setRentalHistory(tempPet.getRentalHistory());
               }
               selectedPet.setID(ID);
           }
       }

        selectedPet.setOwnerEmail(ownerEmail);
        selectedPet.setKindOfPet(kind);
        selectedPet.setName(name);
        selectedPet.setAge(Integer.parseInt(age));
        selectedPet.setGender(petsGender);
        selectedPet.setAvailable(isPetAvailable);

        log.info("selected pet --------------------> {}", selectedPet);

        dynamoDbMapper.save(selectedPet);
        log.info("here <------------------------------------");

        return selectedPet;
    }



}
