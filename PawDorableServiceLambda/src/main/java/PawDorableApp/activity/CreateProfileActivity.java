package PawDorableApp.activity;

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



}
