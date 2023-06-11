package PawDorableApp.activity;

import PawDorableApp.activity.request.GetActiveRentalRequest;
import PawDorableApp.activity.results.GetActiveRentalResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.ActiveRentalDao;
import PawDorableApp.dynamodb.models.ActiveRental;
import PawDorableApp.models.ActiveRentalModel;

import javax.inject.Inject;

public class GetActiveRentalActivity {
    private final ActiveRentalDao activeDao;

    @Inject
    public GetActiveRentalActivity(ActiveRentalDao activeDao) {
        this.activeDao = activeDao;
    }

    public GetActiveRentalResult handleRequest(final GetActiveRentalRequest getRequest){

        ActiveRental selectedActiveRental = activeDao.getActiveRental(getRequest.getRentalID());

        ActiveRentalModel activeRentalModel = new ModelConverter().toActiveRentalModel(selectedActiveRental);

        return GetActiveRentalResult.builder().withActiveRentalModel(activeRentalModel).build();

    }
}
