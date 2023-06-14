package PawDorableApp.activity;

import PawDorableApp.activity.request.RemovePetRequest;
import PawDorableApp.activity.results.RemovePetResult;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.ProfileDao;

import javax.inject.Inject;

public class RemovePetActivity {
    private final PetDao petDao;
    private final ProfileDao profileDao;

    @Inject
    public RemovePetActivity(PetDao petDao, ProfileDao profileDao) {
        this.petDao = petDao;
        this.profileDao = profileDao;
    }

    public RemovePetResult handleRequest(final RemovePetRequest getRequest){

        String petID = getRequest.getID();
        String ownerID = petDao.getPet(petID).getOwnerEmail();
        profileDao.deleteProfilePet(ownerID,petID);
        boolean isRemoved = petDao.removePet(petID);
        return RemovePetResult.builder()
                .withRemove(isRemoved)
                .build();
    }
}
