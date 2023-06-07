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
                       List<String> newRentalHistory, boolean available){

       if(ID == null || ID.isEmpty()
               || kindOfPet == null || kindOfPet.isEmpty()
               || name == null || name.isEmpty()
               || ownerEmail == null || ownerEmail.isEmpty()
               || age == null || age.isEmpty()
               || gender == null || gender.isEmpty()){

           metricsPublisher.addCount(MetricsConstants.UPDATEPET_INVALIDATTRIBUTEVALUE ,1);
           throw new PetInvalidValuesException("could not update profile with current values");

       }

       

        return null;
    }



}
