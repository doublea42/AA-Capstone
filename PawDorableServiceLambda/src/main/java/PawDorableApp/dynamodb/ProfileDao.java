package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.ProfileInvalidValuesException;
import PawDorableApp.exceptions.ProfileNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

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
            metricsPublisher.addCount(MetricsConstants.GET_PROFILE_PROFILE_NOT_FOUND_COUNT, 1);
            throw new ProfileNotFoundException("could not find Profile with email.");
        }

        metricsPublisher.addCount(MetricsConstants.GET_PET_PET_NOT_FOUND_COUNT, 0);
        return selectedProfile;
    }
    public Profile getProfileById(String id){
        Profile selectedProfile = this.dynamoDbMapper.load(Profile.class ,id);
        if(id == null){
            metricsPublisher.addCount(MetricsConstants.GET_PROFILE_PROFILE_NOT_FOUND_COUNT, 1);
            throw new ProfileNotFoundException("could not find Profile with email.");
        }

        metricsPublisher.addCount(MetricsConstants.GET_PET_PET_NOT_FOUND_COUNT, 0);
        return selectedProfile;
    }

    public Profile saveNewProfile(String email, String first, String last, String age){


        if(email == null || email.isEmpty() || first == null || first.isEmpty()
                || last == null || last.isEmpty() || !this.ageCheck(age)){
            metricsPublisher.addCount(MetricsConstants.UPDATE_PROFILE_INVALID_ATTRIBUTE_COUNT, 1);
            throw new ProfileInvalidValuesException("could not update Profile with current values");
        }

        Profile selectedProfile = new Profile();

        selectedProfile.setEmailAddress(email);
        selectedProfile.setFirstName(first);
        selectedProfile.setLastName(last);
        selectedProfile.setAge(Integer.parseInt(age));
        selectedProfile.setMyPets(new HashSet<>());
        selectedProfile.setRental(new HashSet<>());
        selectedProfile.setRentalHistory(new HashSet<>());
        selectedProfile.setFavoriteRental(new HashSet<>());


        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }

    public Profile updateProfile(String email, String first, String last, String age){
        Profile selectedProfile = this.getPofile(email);

        selectedProfile.setFirstName(first);
        selectedProfile.setLastName(last);
        selectedProfile.setAge(Integer.parseInt(age));

        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }

    public Profile addProfilePets(String profileID, String newPet){
        Profile selectedProfile = this.getProfileById(profileID);
        Set<String> tempList = selectedProfile.getMyPets();
        tempList.add(newPet);
        selectedProfile.setMyPets(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }
    public Profile addProfileRental(String profileID, String newRent){
        Profile selectedProfile = this.getProfileById(profileID);
        Set<String> tempList = selectedProfile.getRental();
        tempList.add(newRent);
        selectedProfile.setRental(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }
    public Profile addProfileRentalHistory(String profileID, String newRentalHistory){
        Profile selectedProfile = this.getProfileById(profileID);
        Set<String> rentalHistory = selectedProfile.getRentalHistory();
        rentalHistory.add(newRentalHistory);
        selectedProfile.setRentalHistory(rentalHistory);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }
    public Profile addProfileFavorite(String profileID, String newPet){
        Profile selectedProfile = this.getProfileById(profileID);
        Set<String> tempList = selectedProfile.getFavoriteRental();
        tempList.add(newPet);
        selectedProfile.setFavoriteRental(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }

    public Profile deleteProfilePet(String profileID, String petID){
        Profile selectedProfile = this.getProfileById(profileID);
        Set<String> tempList = selectedProfile.getMyPets();
        tempList.remove(petID);
        selectedProfile.setMyPets(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }
    public Profile deleteProfilerRental(String profileID, String petID){
        Profile selectedProfile = this.getProfileById(profileID);
        Set<String> tempList = selectedProfile.getRental();
        tempList.remove(petID);
        selectedProfile.setRental(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }
    public Profile deleteProfileFav(String profileID, String petID){
        Profile selectedProfile = this.getProfileById(profileID);
        Set<String> tempList = selectedProfile.getFavoriteRental();
        tempList.remove(petID);
        selectedProfile.setFavoriteRental(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }

    private Boolean ageCheck(String age){
        return Integer.parseInt(age) > 18 || Integer.parseInt(age) < 100;
    }

}
