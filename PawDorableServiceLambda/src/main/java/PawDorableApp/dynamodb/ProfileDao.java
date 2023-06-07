package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.ProfileInvalidValuesException;
import PawDorableApp.exceptions.ProfileNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ProfileDao {

    private final Logger log = LogManager.getLogger();
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public ProfileDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Profile getPofile(String email){
        Profile selectedProfile = this.dynamoDbMapper.load(Profile.class ,email);
        if(email == null){
            metricsPublisher.addCount(MetricsConstants.GETP_ROFILE_PROFILE_NOT_FOUND_COUNT, 1);
            throw new ProfileNotFoundException("could not find Profile with email " + email);
        }

        metricsPublisher.addCount(MetricsConstants.GETPET_PETNOTFOUND_COUNT, 0);
        return selectedProfile;
    }

    public Profile saveProfile(boolean isNew, String id, String email, String first, String last,
                               String age, List<String> myPets, List<String> rental,
                               List<String> rentalHistory, List<String> favorite){


        if(email == null || email.isEmpty() || first == null || first.isEmpty()
                || last == null || last.isEmpty() || !this.ageCheck(age)){
            metricsPublisher.addCount(MetricsConstants.UPDATE_PROFILE_INVALID_ATTRIBUTE_COUNT, 1);
            throw new ProfileInvalidValuesException("could not update Profile with current values");
        }

        Profile selectedProfile = new Profile();

        if(isNew){
            selectedProfile.setID(PawDorableServiceUtils.generateId());
            selectedProfile.setMyPets(new ArrayList<String>());
            selectedProfile.setRental(new ArrayList<String>());
            selectedProfile.setRentalHistory(new ArrayList<String>());
            selectedProfile.setFavoriteRental(new ArrayList<String>());

        }
        else{

            Profile tempProfile = this.getPofile(email);

            if(!myPets.isEmpty()){
                List<String> tempList = tempProfile.getMyPets();
                tempList.addAll(myPets);
                selectedProfile.setMyPets(tempList);
            }else{selectedProfile.setMyPets(tempProfile.getMyPets());}
            if(!rental.isEmpty()){
                List<String> tempList = tempProfile.getRental();
                tempList.addAll(rental);
                selectedProfile.setRental(tempList);
            }else{selectedProfile.setRental(tempProfile.getRental());}
            if(!rentalHistory.isEmpty()){
                List<String> tempList = tempProfile.getRentalHistory();
                tempList.addAll(rentalHistory);
                selectedProfile.setRentalHistory(tempList);
            }else{selectedProfile.setRentalHistory(tempProfile.getRentalHistory());}
            if(!favorite.isEmpty()){
                List<String> tempList = tempProfile.getFavoriteRental();
                tempList.addAll(favorite);
                selectedProfile.setFavoriteRental(tempList);
            }else{selectedProfile.setFavoriteRental(tempProfile.getFavoriteRental());}

            selectedProfile.setID(id);
        }


        selectedProfile.setFirstName(first);
        selectedProfile.setLastName(last);
        selectedProfile.setAge(Integer.parseInt(age));
        selectedProfile.setEmailAddress(email);


        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }

    private Boolean ageCheck(String age){
        return Integer.parseInt(age) > 18 || Integer.parseInt(age) < 100;
    }

}
