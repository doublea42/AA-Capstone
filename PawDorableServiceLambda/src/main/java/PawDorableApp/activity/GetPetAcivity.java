package PawDorableApp.activity;

import PawDorableApp.activity.request.GetPetRequest;
import PawDorableApp.activity.results.GetPetResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.models.PetModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetPetAcivity {
    private final Logger log = LogManager.getLogger();
    private final PetDao petDao;

    @Inject
    public GetPetAcivity(PetDao petDao) {
        this.petDao = petDao;
    }

    public GetPetResult handleRequest(final GetPetRequest getRequest){

        Pet selectedPet = petDao.getPet(getRequest.getID());

        PetModel petModel = new ModelConverter().toPetModel(selectedPet);

        return GetPetResult.builder()
                .withPet(petModel)
                .build();
    }
}
