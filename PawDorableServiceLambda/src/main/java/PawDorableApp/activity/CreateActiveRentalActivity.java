package PawDorableApp.activity;

import PawDorableApp.activity.request.CreateActiveRentalRequest;
import PawDorableApp.activity.results.CreateActiveRentalResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ActiveRentalDao;
import PawDorableApp.dynamodb.ProfileDao;
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
    private final ProfileDao profileDao;

    @Inject
    public CreateActiveRentalActivity(ActiveRentalDao activeDao, RentalHistoryDao rentalDao,
                                      ProfileDao profileDao) {
        this.activeDao = activeDao;
        this.rentalDao = rentalDao;
        this.profileDao = profileDao;
    }

    public CreateActiveRentalResult handleRequest(final CreateActiveRentalRequest createRequest){
        log.info("Received CreateActiveRentalRequest{}",createRequest);

        String petID = createRequest.getPetID();
        String profileID = createRequest.getProfileID();

        log.info("----------------------> check1 <-------------------------");

        RentalHistory newRentalHistory = rentalDao.saveRentalHistory(petID, profileID);
        log.info("----------------------> check2 <-------------------------");


        profileDao.addProfileRental(profileID, petID);
        log.info("----------------------> check3 <-------------------------");


        ActiveRental newActiveRental = activeDao.saveNewActiveRental(newRentalHistory.getID());
        log.info("----------------------> check4 <-------------------------");


        ActiveRentalModel activeRentalModel = new ModelConverter().toActiveRentalModel(newActiveRental);
        log.info("----------------------> check5 <-------------------------");

        return CreateActiveRentalResult.builder().withActiveRental(activeRentalModel).build();
    }
}
