package PawDorableApp.activity;

import PawDorableApp.activity.request.GetProfileRequest;
import PawDorableApp.activity.results.GetProfileResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.ProfileInvalidValuesException;
import PawDorableApp.models.ProfileModel;
import PawDorableApp.utils.PawDorableServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final ProfileDao profileDao;

    @Inject
    public GetProfileActivity(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public GetProfileResult handleRequest(final GetProfileRequest getProfileRequest){
        log.info("Received GetProfileRequest{}", getProfileRequest);

        if(!PawDorableServiceUtils.isValidString(getProfileRequest.getEmailAddress())){
            throw new ProfileInvalidValuesException("Your email cannot contain illegal characters");
        }

        Profile selectedProfile = profileDao.getPofile(getProfileRequest.getEmailAddress());

        ProfileModel profileModel = new ModelConverter().toProfileModel(selectedProfile);

        GetProfileResult.builder();
        return GetProfileResult.builder()
                .withProfile(profileModel)
                .build();
    }

}
