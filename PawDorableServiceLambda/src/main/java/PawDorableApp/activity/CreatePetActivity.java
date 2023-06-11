package PawDorableApp.activity;

import PawDorableApp.activity.request.CreatePetRequest;
import PawDorableApp.activity.results.CreatePetResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.PetInvalidValuesException;
import PawDorableApp.models.PetModel;
import PawDorableApp.utils.PawDorableServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class CreatePetActivity {
    private final Logger log = LogManager.getLogger();
    private final PetDao petDao;
    private final ProfileDao profileDao;

    @Inject
    public CreatePetActivity(PetDao petDao, ProfileDao profileDao) {
        this.petDao = petDao;
        this.profileDao = profileDao;
    }


    public CreatePetResult handleRequest(final CreatePetRequest createPetRequest){
        if(PawDorableServiceUtils.invalidString(createPetRequest.getName())){
            throw new PetInvalidValuesException("Your Name cannot contain illegal characters");
        }
        String ownerID = createPetRequest.getOwnerEmail();

        Pet newPet = petDao.saveNewPet(createPetRequest.getKindOfPet(), createPetRequest.getName(), ownerID,
                createPetRequest.getAge(), createPetRequest.getGender(), createPetRequest.getAvailable());

        profileDao.addProfilePets(ownerID, newPet.getID());

        PetModel petmodel = new ModelConverter().toPetModel(newPet);

        return CreatePetResult.builder().withPet(petmodel).build();
    }


}
