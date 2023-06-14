package PawDorableApp.activity;

import PawDorableApp.activity.request.RemoveActiveRentalRequest;
import PawDorableApp.activity.results.RemoveActiveRentalResult;
import PawDorableApp.dynamodb.ActiveRentalDao;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.RentalHistoryDao;
import PawDorableApp.dynamodb.models.ActiveRental;
import PawDorableApp.dynamodb.models.RentalHistory;

import javax.inject.Inject;

public class RemoveActiveRentalActivity {
    private final ActiveRentalDao activeDao;
    private final RentalHistoryDao rentalDao;
    private final PetDao petDao;
    private final ProfileDao profileDao;

    @Inject
    public RemoveActiveRentalActivity(ActiveRentalDao activeDao, RentalHistoryDao rentalDao, PetDao petDao, ProfileDao profileDao) {
        this.activeDao = activeDao;
        this.rentalDao = rentalDao;
        this.petDao = petDao;
        this.profileDao = profileDao;
    }

    public RemoveActiveRentalResult handleRequest(final RemoveActiveRentalRequest getRequest){

        String petID = getRequest.getPetID();
        String profileID = getRequest.getProfileID();
        String score = getRequest.getScore();

        RentalHistory updateRentalHistory = rentalDao.findRentalHistory(petID, profileID);
        String historyID = updateRentalHistory.getID();
        rentalDao.UpdateRentalHistory(historyID, score);

        profileDao.deleteProfilerRental(profileID, petID);
        profileDao.addProfileRentalHistory(profileID, historyID, updateRentalHistory.getScore());
        petDao.updateAvailablePet(petID, "true");


        boolean result = activeDao.removeActiveRental("AR_" + historyID);

        return RemoveActiveRentalResult.builder().withRemove(result).build();
    }
}
