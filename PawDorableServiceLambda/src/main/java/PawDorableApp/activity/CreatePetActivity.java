package PawDorableApp.activity;

import PawDorableApp.activity.results.CreatePetResult;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.utils.PawDorableServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreatePetActivity {
    private final Logger log = LogManager.getLogger();
    private final PetDao petDao;

    @Inject
    public CreatePetActivity(PetDao petDao) {
        this.petDao = petDao;
    }

//    public CreatePetResult handleRequest(final CreatePetResult createPetRequest){
//        log.info("Received CreateRequest{}", createPetRequest);
//
//        if(!PawDorableServiceUtils.isValidString())
//
//        return null;
//    }


}
