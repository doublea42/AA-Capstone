package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.exceptions.PetNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

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
            metricsPublisher.addCount(MetricsConstants.GETPET_PETNOTFOUND_COUNT, 1);
            throw new PetNotFoundException("could not find Pet with id " + petID);
        }

        metricsPublisher.addCount(MetricsConstants.GETPET_PETNOTFOUND_COUNT, 0);
        return selectedPet;
    }


}
