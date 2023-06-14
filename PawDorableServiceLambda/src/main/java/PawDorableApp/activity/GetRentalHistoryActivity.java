package PawDorableApp.activity;

import PawDorableApp.activity.request.GetRentalHistoryRequest;
import PawDorableApp.activity.results.GetRentalHistoryResult;
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

    public GetRentalHistoryResult handleRequest(final GetRentalHistoryRequest getRequest){

        RentalHistory selectedRental = rentalDao.getRentalHistory(getRequest.getId());

        RentalHistoryModel rentalModel = new ModelConverter().toRentalHistoryModel(selectedRental);

        return GetRentalHistoryResult.builder()
                .withRentalModel(rentalModel)
                .build();
    }
}
