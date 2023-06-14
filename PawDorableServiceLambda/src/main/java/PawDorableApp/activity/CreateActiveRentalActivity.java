package PawDorableApp.activity;

import PawDorableApp.activity.request.CreateActiveRentalRequest;
import PawDorableApp.activity.results.CreateActiveRentalResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ActiveRentalDao;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.RentalHistoryDao;
import PawDorableApp.dynamodb.models.ActiveRental;
import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.exceptions.PetIsunavailableException;
import PawDorableApp.models.ActiveRentalModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateActiveRentalActivity {
    private final Logger log = LogManager.getLogger();
    private final ActiveRentalDao activeDao;
    private final RentalHistoryDao rentalDao;
    private final ProfileDao profileDao;
    private final PetDao petDao;

    @Inject
    public CreateActiveRentalActivity(ActiveRentalDao activeDao, RentalHistoryDao rentalDao,
                                      ProfileDao profileDao, PetDao petDao) {
        this.activeDao = activeDao;
        this.rentalDao = rentalDao;
        this.profileDao = profileDao;
        this.petDao = petDao;
    }

    public CreateActiveRentalResult handleRequest(final CreateActiveRentalRequest createRequest){
        log.info("Received CreateActiveRentalRequest{}",createRequest);

        String petID = createRequest.getPetID();
        String profileID = createRequest.getProfileID();


        RentalHistory newRentalHistory = rentalDao.saveRentalHistory(petID, profileID);
        String rentalHistoryID = newRentalHistory.getID();

        petDao.isAvailable(petID);
        profileDao.addProfileRental(profileID, petID);
        profileDao.addProfileRentalHistory(profileID, rentalHistoryID, newRentalHistory.getScore());
        petDao.addRentalHistory(petID, rentalHistoryID);
        petDao.updateAvailablePet(petID, "false");

        ActiveRental newActiveRental = activeDao.saveNewActiveRental(rentalHistoryID);
        ActiveRentalModel activeRentalModel = new ModelConverter().toActiveRentalModel(newActiveRental);
        return CreateActiveRentalResult.builder().withActiveRental(activeRentalModel).build();
    }
}
