package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.exceptions.PetInvalidValuesException;
import PawDorableApp.exceptions.PetNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import PawDorableApp.models.Gender;
import PawDorableApp.models.KindOfPet;
import PawDorableApp.utils.IdGenerator;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public Pet savePet(boolean isNew, String ID, Enum<KindOfPet> kindOfPet, String name,
                       String ownerID, int age, Enum<Gender> gender,
                       List<String> newRentalHistory, boolean available){

        Pet selectedPet = new Pet();

        if(isNew){
            selectedPet.setID(IdGenerator.generateId());
            selectedPet.setRentalHistory(new ArrayList<>(newRentalHistory));
        }


        if(name != null || !name.isEmpty() || kindOfPet != null
                || ownerID !=null || !ownerID.isEmpty() || gender !=null){

            selectedPet.setKindOfPet(kindOfPet);
            selectedPet.setName(name);
            selectedPet.setOwnerID(ownerID);
            selectedPet.setAge(age);
            selectedPet.setGender(gender);
            selectedPet.setAvailable(available);

            if(!newRentalHistory.isEmpty()){
                Pet oldPet = this.getPet(ID);
                List<String> previosList = oldPet.getRentalHistory();
                previosList.addAll(newRentalHistory);
                selectedPet.setRentalHistory(previosList);
            }
        }
        else{
            metricsPublisher.addCount(MetricsConstants.UPDATEPET_INVALIDATTRIBUTEVALUE, 1);
            throw new PetInvalidValuesException("could not update pet with current values");
        }

        this.dynamoDbMapper.save(selectedPet);
        return selectedPet;
    }



}
