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

        Set<String> myPets = new HashSet<>();
        Set<String> rental = new HashSet<>();
        Set<String> rentalHistory = new HashSet<>();
        Set<String> activeRental = new HashSet<>();
        this.checkEmpty(myPets);
        this.checkEmpty(rental);
        this.checkEmpty(rentalHistory);
        this.checkEmpty(activeRental);


        selectedProfile.setMyPets(myPets);
        selectedProfile.setRental(rental);
        selectedProfile.setRentalHistory(rentalHistory);
        selectedProfile.setFavoriteRental(activeRental);


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

    public void addProfilePets(String profileID, String newPet){
        Profile selectedProfile = this.getPofile(profileID);
        Set<String> tempList = selectedProfile.getMyPets();
        this.checkEmpty(tempList);
        tempList.add(newPet);
        selectedProfile.setMyPets(tempList);
        dynamoDbMapper.save(selectedProfile);
    }
    public void addProfileRental(String profileID, String newRent){
        Profile selectedProfile = this.getPofile(profileID);

        Set<String> tempList = selectedProfile.getRental();
        this.checkEmpty(tempList);
        tempList.add(newRent);
        selectedProfile.setRental(tempList);

        dynamoDbMapper.save(selectedProfile);
    }
    public Profile addProfileRentalHistory(String profileID, String newRentalHistory, double score){

        Profile selectedProfile = this.getPofile(profileID);
        Set<String> rentalHistory = selectedProfile.getRentalHistory();
        this.checkEmpty(rentalHistory);

        rentalHistory.add(newRentalHistory);
        selectedProfile.setRentalHistory(rentalHistory);

        Set<String> tempFavList = selectedProfile.getFavoriteRental();
        boolean isFavorite = tempFavList.contains(newRentalHistory);

        if(isFavorite && score < 4.0){
            tempFavList.remove(newRentalHistory);
            this.checkEmpty(tempFavList);
            selectedProfile.setFavoriteRental(tempFavList);
        }
        else if(!isFavorite && score > 3.9){
            this.checkEmpty(tempFavList);
            tempFavList.add(newRentalHistory);
            selectedProfile.setFavoriteRental(tempFavList);
        }

        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }


    public Profile deleteProfilePet(String profileID, String petID){
        Profile selectedProfile = this.getPofile(profileID);
        Set<String> tempList = selectedProfile.getMyPets();
        tempList.remove(petID);
        this.checkEmpty(tempList);
        selectedProfile.setMyPets(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }
    public Profile deleteProfilerRental(String profileID, String petID){
        Profile selectedProfile = this.getPofile(profileID);
        Set<String> tempList = selectedProfile.getRental();
        tempList.remove(petID);
        this.checkEmpty(tempList);
        selectedProfile.setRental(tempList);
        dynamoDbMapper.save(selectedProfile);
        return selectedProfile;
    }

    private Boolean ageCheck(String age){
        return Integer.parseInt(age) > 18 || Integer.parseInt(age) < 100;
    }
    private void checkEmpty(Set<String> tempList){
        String temp = "0";
        if(tempList.size() == 1){
            tempList.remove(temp);
        }
        if(tempList.isEmpty()){
            tempList.add(temp);
        }
    }

}
