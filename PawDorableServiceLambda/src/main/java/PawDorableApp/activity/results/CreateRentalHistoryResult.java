package PawDorableApp.activity.results;

import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.models.RentalHistoryModel;

public class CreateRentalHistoryResult {
    private final RentalHistoryModel rentalHistory;

    public CreateRentalHistoryResult(RentalHistoryModel rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    public RentalHistoryModel getRentalHistory() {
        return rentalHistory;
    }

    @Override
    public String toString() {
        return "CreateRentalHistoryResult{" +
                "rentalHistory=" + rentalHistory +
                '}';
    }

    public static Builder builder(){return new Builder();}
    public static class Builder{
        private  RentalHistoryModel rentalHistory;

        public Builder withRentalHistory(RentalHistoryModel rentalHistory){
            this.rentalHistory = rentalHistory;
            return this;
        }

        public CreateRentalHistoryResult build() { return new CreateRentalHistoryResult(rentalHistory); }

    }

}
