package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.PetNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ProfileDao {

    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public ProfileDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Profile getPofile(String profileID){
        Profile selectedProfile = this.dynamoDbMapper.load(Profile.class ,profileID);
        if(profileID == null){
            metricsPublisher.addCount(MetricsConstants.GETPROFILE_PROFILENOTFOUND_COUNT, 1);
            throw new PetNotFoundException("could not find Profile with id " + profileID);
        }

        metricsPublisher.addCount(MetricsConstants.GETPET_PETNOTFOUND_COUNT, 0);
        return selectedProfile;
    }

    public Profile saveProfile(boolean isNew, String id, String email, String first, String last,
                               int age, List<String> myPets, List<String> rental,
                               List<String> rentalHistory, List<String> favorite){

        return null;
    }



}
