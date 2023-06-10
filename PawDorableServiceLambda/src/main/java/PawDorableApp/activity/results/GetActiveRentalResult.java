package PawDorableApp.activity.results;

import PawDorableApp.models.ActiveRentalModel;
import com.amazonaws.services.dynamodbv2.xspec.S;

public class GetActiveRentalResult {
    private final ActiveRentalModel activeRental;

    public GetActiveRentalResult(ActiveRentalModel activeRental) {
        this.activeRental = activeRental;
    }

    public ActiveRentalModel getActiveRental() {
        return activeRental;
    }

    @Override
    public String toString() {
        return "GetActiveRentalResult{" +
                "activeRental=" + activeRental +
                '}';
    }
    public Builder builder(){return new Builder();}
    public static class Builder{
        private ActiveRentalModel activeRentalModel;
        public Builder withActiveRentalModel(ActiveRentalModel activeRental){
            this.activeRentalModel =activeRental;
            return this;
        }
        public GetActiveRentalResult build(){return new GetActiveRentalResult(activeRentalModel);}
    }
}
