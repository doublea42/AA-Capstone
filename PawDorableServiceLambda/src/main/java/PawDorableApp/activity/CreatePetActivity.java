package PawDorableApp.activity;

import PawDorableApp.activity.request.CreatePetRequest;
import PawDorableApp.activity.results.CreatePetResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.exceptions.PetInvalidValuesException;
import PawDorableApp.models.PetModel;
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

    public CreatePetResult handleRequest(final CreatePetRequest createPetRequest){
        log.info("Received CreateRequest{}", createPetRequest);

        if(!PawDorableServiceUtils.isValidString(createPetRequest.getName())){
            throw new PetInvalidValuesException("Your Name cannot contain illegal characters");
        }

        Pet newPet = petDao.savePet(true,"", createPetRequest.getKind(), createPetRequest.getName(),
                createPetRequest.getOwnerEmail(), createPetRequest.getAge(), createPetRequest.getGender(),
                null, Boolean.parseBoolean(createPetRequest.getAvailable()));

        PetModel petModel = new ModelConverter().toPetModel(newPet);
        return CreatePetResult.builder().withPet(petModel).build();
    }


}
