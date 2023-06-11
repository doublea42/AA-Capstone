package PawDorableApp.activity;

import PawDorableApp.activity.request.CreateRentalHistoryRequest;
import PawDorableApp.activity.results.CreateActiveRentalResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ActiveRentalDao;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.RentalHistoryDao;
import PawDorableApp.dynamodb.models.ActiveRental;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.models.ActiveRentalModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateActiveRentalActivity {
    private final Logger log = LogManager.getLogger();
    private final ActiveRentalDao activeDao;
    private final RentalHistoryDao rentalDao;
    private final ProfileDao profileDao;
    private final PawDorableApp.dynamodb.PetDao petDao;

    @Inject
    public CreateActiveRentalActivity(ActiveRentalDao activeDao, RentalHistoryDao rentalDao, ProfileDao profileDao, PetDao petDao) {
        this.activeDao = activeDao;
        this.rentalDao = rentalDao;
        this.profileDao = profileDao;
        this.petDao = petDao;
    }

    public CreateActiveRentalResult handleRequest(final CreateRentalHistoryRequest createRequest){
        log.info("Received CreateActiveRentalRequest{}",createRequest);

        RentalHistory newRentalHistory = rentalDao.saveRentalHistory(true, "", createRequest.getPetID(), createRequest.getProfileID(), 0.0);
        ActiveRental newActiveRental = activeDao.saveActiveRental(true,"", newRentalHistory.getHistoryID());

        ActiveRentalModel activeRentalModel = new ModelConverter().toActiveRentalModel(newActiveRental);
        return CreateActiveRentalResult.builder().withActiveRental(activeRentalModel).build();
    }
}
