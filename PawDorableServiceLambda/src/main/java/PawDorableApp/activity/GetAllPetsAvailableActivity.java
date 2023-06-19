package PawDorableApp.activity;

import PawDorableApp.activity.request.GetAllPetsAvailableRequest;
import PawDorableApp.activity.results.GetAllPetsAvailableResult;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.models.Pet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetAllPetsAvailableActivity {
    private final Logger log = LogManager.getLogger();
    private final PetDao petDao;

    @Inject
    public GetAllPetsAvailableActivity(PetDao petDao) {
        this.petDao = petDao;
    }

    public GetAllPetsAvailableResult handleRequest(final GetAllPetsAvailableRequest getRequest){
        List<Pet> listOfPets = petDao.getAllPetsAvailable(getRequest.getProfilesID());
        return  GetAllPetsAvailableResult.builder()
                .withListOfPets(listOfPets)
                .build();
    }
}
