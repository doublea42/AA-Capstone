package PawDorableApp.activity;

import PawDorableApp.activity.request.UpdatePetRequest;
import PawDorableApp.activity.results.UpdatePetResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.models.PetModel;

import javax.inject.Inject;

public class UpdatePetActivity {
    private final PetDao petDao;


    @Inject
    public UpdatePetActivity(PetDao petDao) {
        this.petDao = petDao;
    }

    public UpdatePetResult handleRequest(final UpdatePetRequest updateRequest){

        Pet oldPet = petDao.getPet(updateRequest.getID());

        Pet updatePet = petDao.savePet(false, updateRequest.getID(), oldPet.getKindOfPet().toString(), oldPet.getName(), oldPet.getOwnerEmail(),
                updateRequest.getAge(), oldPet.getGender().toString(),null,updateRequest.getAvailable());

        PetModel petModel = new ModelConverter().toPetModel(updatePet);
        return UpdatePetResult.builder().withPetModel(petModel).build();
    }
}
