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
    RemoveActiveRentalActivity(ActiveRentalDao activeDao, RentalHistoryDao rentalDao, PetDao petDao, ProfileDao profileDao) {
        this.activeDao = activeDao;
        this.rentalDao = rentalDao;
        this.petDao = petDao;
        this.profileDao = profileDao;
    }

    public RemoveActiveRentalResult handleRequest(final RemoveActiveRentalRequest getRequest){

        ActiveRental rentalEnded = activeDao.getActiveRental(getRequest.getRentalID());
        RentalHistory updateRentalHistory = rentalDao.UpdateRentalHistory(rentalEnded.getRentalHistory(), getRequest.getScore());
        String historyID = updateRentalHistory.getHistoryID();
        petDao.addRentalHistory(updateRentalHistory.getPetID(), historyID);
        profileDao.addProfileRentalHistory(updateRentalHistory.getProfileID(), historyID);

        boolean result = activeDao.removeActiveRental(rentalEnded.getRentalID());

        return RemoveActiveRentalResult.builder().withRemove(result).build();
    }
}
