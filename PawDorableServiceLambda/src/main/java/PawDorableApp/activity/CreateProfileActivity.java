package PawDorableApp.activity;

import PawDorableApp.activity.request.CreateProfileRequest;
import PawDorableApp.activity.results.CreateProfileResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.ProfileInvalidValuesException;
import PawDorableApp.models.ProfileModel;
import PawDorableApp.utils.PawDorableServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final ProfileDao profileDao;

    @Inject
    public CreateProfileActivity(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }


    public CreateProfileResult handleRequest(final CreateProfileRequest createProfileRequest){
        log.info("Received CreateProfileRequest{}", createProfileRequest);

        if(!PawDorableServiceUtils.isValidString(createProfileRequest.getFirstName())
                || !PawDorableServiceUtils.isValidString(createProfileRequest.getLastName())){
            throw new ProfileInvalidValuesException("Your Name cannot contain illegal characters");
        }
        if(!PawDorableServiceUtils.validAge(Integer.parseInt(createProfileRequest.getAge()))){
            throw new ProfileInvalidValuesException("Your age is out range");
        }

        Profile newProfile = profileDao.saveProfile(true,"", createProfileRequest.getEmailAddress(),
                createProfileRequest.getFirstName(), createProfileRequest.getLastName(), createProfileRequest.getAge(),
               null, null, null, null);

        ProfileModel profileModel = new ModelConverter().toProfileModel(newProfile);
        return CreateProfileResult.builder()
                .withProfile(profileModel)
                .build();

    }
}
