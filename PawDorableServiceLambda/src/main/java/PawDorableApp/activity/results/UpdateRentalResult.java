package PawDorableApp.activity.results;

import PawDorableApp.models.RentalHistoryModel;

public class UpdateRentalResult {
    private final RentalHistoryModel rental;

    public UpdateRentalResult(RentalHistoryModel rental) {
        this.rental = rental;
    }

    public RentalHistoryModel getRental() {
        return rental;
    }

    @Override
    public String toString() {
        return "UpdateRentalResult{" +
                "rental=" + rental +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RentalHistoryModel rental;
        public Builder withRentalModel(RentalHistoryModel rental){
            this.rental = rental;
            return this;
        }
        public UpdateRentalResult build(){return new UpdateRentalResult(rental);}
    }

}
