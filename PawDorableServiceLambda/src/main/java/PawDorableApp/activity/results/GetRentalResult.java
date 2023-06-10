package PawDorableApp.activity.results;


import PawDorableApp.models.RentalHistoryModel;

public class GetRentalResult {
    private final RentalHistoryModel rental;

    public GetRentalResult(RentalHistoryModel rental) {
        this.rental = rental;
    }

    public RentalHistoryModel getRental() {
        return rental;
    }

    @Override
    public String toString() {
        return "GetRentalResult{" +
                "rental=" + rental +
                '}';
    }

    public static Builder builder(){return new Builder();}
    public static class Builder{
        private RentalHistoryModel rental;

        public Builder withRentalModel(RentalHistoryModel rental){
            this.rental = rental;
            return this;
        }
        public GetRentalResult build(){return new GetRentalResult(rental);}

    }
}
