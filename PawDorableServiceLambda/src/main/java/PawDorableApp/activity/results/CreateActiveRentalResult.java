package PawDorableApp.activity.results;

import PawDorableApp.models.ActiveRentalModel;

public class CreateActiveRentalResult {
    private final ActiveRentalModel activeRental;

    public CreateActiveRentalResult(ActiveRentalModel activeRental) {
        this.activeRental = activeRental;
    }

    public ActiveRentalModel getActiveRental() {
        return activeRental;
    }

    @Override
    public String toString() {
        return "CreateActiveRentalResult{" +
                "activeRental=" + activeRental +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder{
        private  ActiveRentalModel activeRental;

        public Builder withActiveRental(ActiveRentalModel activeRental){
            this.activeRental = activeRental;
            return this;
        }

        public CreateActiveRentalResult build() {return new CreateActiveRentalResult(activeRental);}

    }

}
