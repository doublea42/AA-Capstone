package PawDorableApp.activity;

import PawDorableApp.activity.request.CreateRentalHistoryRequest;
import PawDorableApp.activity.results.CreateActiveRentalResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ActiveRentalDao;
import PawDorableApp.dynamodb.RentalHistoryDao;
import PawDorableApp.dynamodb.models.ActiveRental;
import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.models.ActiveRentalModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateActiveRentalActivity {
    private final Logger log = LogManager.getLogger();
    private final ActiveRentalDao activeDao;
    private final RentalHistoryDao rentalDao;

    @Inject
    public CreateActiveRentalActivity(ActiveRentalDao activeDao, RentalHistoryDao rentalDao) {
        this.activeDao = activeDao;
        this.rentalDao = rentalDao;
    }

    public CreateActiveRentalResult handleRequest(final CreateRentalHistoryRequest createRequest){
        log.info("Received CreateActiveRentalRequest{}",createRequest);

        RentalHistory newRentalHistory = rentalDao.saveRentalHistory(true, "", createRequest.getPetID(), createRequest.getProfileID(), 0.0);
        ActiveRental newActiveRental = activeDao.saveActiveRental(true,"", newRentalHistory.getHistoryID());

        ActiveRentalModel activeRentalModel = new ModelConverter().toActiveRentalModel(newActiveRental);
        return CreateActiveRentalResult.builder().withActiveRental(activeRentalModel).build();
    }

}
