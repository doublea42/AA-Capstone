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

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PetDao {

//    private final Logger log = LogManager.getLogger();
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
            metricsPublisher.addCount(MetricsConstants.GETPET_PETNOTFOUND_COUNT, 1);
            throw new PetNotFoundException("could not find Pet with id " + petID);
        }

        metricsPublisher.addCount(MetricsConstants.GETPET_PETNOTFOUND_COUNT, 0);
        return selectedPet;
    }

    public Pet savePet(boolean isNew, String ID, String kindOfPet, String name,
                       String ownerEmail, String age, String gender,
                       List<String> rentalHistory, boolean available){

       Enum<KindOfPet> kind = PawDorableServiceUtils.petEnum(kindOfPet);
       Enum<Gender> petsGender = PawDorableServiceUtils.genderEnum(gender);

        if(ID == null || ID.isEmpty()
               || kind == null
               || name == null || name.isEmpty()
               || ownerEmail == null || ownerEmail.isEmpty()
               || age == null || age.isEmpty()
               || petsGender == null){

           metricsPublisher.addCount(MetricsConstants.UPDATEPET_INVALIDATTRIBUTEVALUE ,1);
           throw new PetInvalidValuesException("could not update profile with current values");

       }

        Pet selectedPet = new Pet();

       if(isNew){
           selectedPet.setID(PawDorableServiceUtils.generateId());
           selectedPet.setRentalHistory(new ArrayList<String>());
       }
       else{
           Pet tempPet = this.getPet(ID);
           if(!rentalHistory.isEmpty()){
               List<String> history = tempPet.getRentalHistory();
               history.addAll(rentalHistory);
               selectedPet.setRentalHistory(history);
           }else{selectedPet.setRentalHistory(selectedPet.getRentalHistory());}
           selectedPet.setID(ID);
       }

       selectedPet.setKindOfPet(kind);
       selectedPet.setName(name);
       selectedPet.setOwnerEmail(ownerEmail);
       selectedPet.setAge(Integer.parseInt(age));
       selectedPet.setGender(petsGender);
       selectedPet.setAvailable(available);



        dynamoDbMapper.save(selectedPet);
        return selectedPet;
    }



}
