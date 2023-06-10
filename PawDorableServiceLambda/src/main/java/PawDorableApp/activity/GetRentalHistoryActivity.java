package PawDorableApp.activity;

import PawDorableApp.activity.request.GetRentalRequest;
import PawDorableApp.activity.results.GetRentalResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.RentalHistoryDao;
import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.models.RentalHistoryModel;

import javax.inject.Inject;

public class GetRentalHistoryActivity {
    private final RentalHistoryDao rentalDao;

    @Inject
    public GetRentalHistoryActivity(RentalHistoryDao rentalDao) {
        this.rentalDao = rentalDao;
    }

    public GetRentalResult handleRequest(final GetRentalRequest getRequest){

        RentalHistory selectedRental = rentalDao.getRentalHistory(getRequest.getHistoryID());

        RentalHistoryModel rentalModel = new ModelConverter().toRentalHistoryModel(selectedRental);

        return GetRentalResult.builder()
                .withRentalModel(rentalModel)
                .build();
    }
}
