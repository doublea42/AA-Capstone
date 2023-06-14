package PawDorableApp.activity.results;


import PawDorableApp.models.RentalHistoryModel;

public class GetRentalHistoryResult {
    private final RentalHistoryModel rentalHistoryModel;

    public GetRentalHistoryResult(RentalHistoryModel rental) {
        this.rentalHistoryModel = rental;
    }

    public RentalHistoryModel getRentalHistoryModel() {
        return rentalHistoryModel;
    }

    @Override
    public String toString() {
        return "GetRentalResult{" +
                "rentalHistoryModel=" + rentalHistoryModel +
                '}';
    }

    public static Builder builder(){return new Builder();}
    public static class Builder{
        private RentalHistoryModel rentalHistoryModel;

        public Builder withRentalModel(RentalHistoryModel rental){
            this.rentalHistoryModel = rental;
            return this;
        }
        public GetRentalHistoryResult build(){return new GetRentalHistoryResult(rentalHistoryModel);}

    }
}
