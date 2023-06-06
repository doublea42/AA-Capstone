package PawDorableApp.activity;

import PawDorableApp.activity.request.UpdateProfileRequest;
import PawDorableApp.activity.results.UpdateProfileResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.ProfileInvalidValuesException;
import PawDorableApp.models.ProfileModel;
import PawDorableApp.utils.PawDorableServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateProfileActivity {
    private final Logger log = LogManager.getLogger();

    private final ProfileDao profileDao;

    @Inject
    public UpdateProfileActivity(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public UpdateProfileResult handleRequest(final UpdateProfileRequest updateProfileRequest){
        if(!PawDorableServiceUtils.isValidString(updateProfileRequest.getFirstName())
                || !PawDorableServiceUtils.isValidString(updateProfileRequest.getLastName())
                || !PawDorableServiceUtils.validAge(Integer.parseInt(updateProfileRequest.getAge()))){
            throw new ProfileInvalidValuesException("Your Name cannot contain illegal characters");
        }


        Profile oldProfile = profileDao.getPofile(updateProfileRequest.getEmailAddress());

        Profile updatedProfile = profileDao.saveProfile(false, oldProfile.getID(), oldProfile.getEmailAddress()
                , updateProfileRequest.getFirstName(), updateProfileRequest.getLastName(), updateProfileRequest.getAge(),
                oldProfile.getMyPets(),oldProfile.getRental(), oldProfile.getRentalHistory(), oldProfile.getFavoriteRental());

        ProfileModel profileModel = new ModelConverter().toProfileModel(updatedProfile);
        return UpdateProfileResult.builder().withProfile(profileModel).build();
    }
}
