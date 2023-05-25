package PawDorableApp.activity;

import PawDorableApp.activity.request.CreateProfileRequest;
import PawDorableApp.activity.results.CreateProfileResult;
import PawDorableApp.dynamodb.ProfileDao;
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

        //
//        if(!MusicPlaylistServiceUtils.isValidString(createProfileRequest.getFirstName())){
//            throw new InvalidAttributeValueException("Your Name cannot contain illegal characters");
//        }
//
//        Profile newProfile = profileDao.saveProfile(true,
//                createProfileRequest.getId(), createProfileRequest.getFirstName(),
//                createProfileRequest.getLastName(), createProfileRequest.getLocation(),
//                createProfileRequest.getGender(), ZonedDateTime.parse(createProfileRequest.getDateOfBirth()));
//
//        ProfileModel profileModel = new ModelConverter().toProfileModel(newProfile);
//        return CreateProfileResult.builder()
//                .withProfile(profileModel)
//                .build();
//    }
}
