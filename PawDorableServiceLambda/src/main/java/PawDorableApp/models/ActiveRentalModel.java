package PawDorableApp.models;

import java.util.List;
import java.util.Objects;

public class ActiveRentalModel {
    private final String rentalID;
    private final List<String> rentalHistory;

    public ActiveRentalModel(String rentalID, List<String> rentalHistory) {
        this.rentalID = rentalID;
        this.rentalHistory = rentalHistory;
    }

    public String getRentalID() {
        return rentalID;
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveRentalModel that = (ActiveRentalModel) o;
        return Objects.equals(rentalID, that.rentalID)
                && Objects.equals(rentalHistory, that.rentalHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalID, rentalHistory);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String rentalID;
        private List<String> rentalHistory;

        public Builder withRentalID(String id){
            this.rentalID = id;
            return this;
        }
        public Builder withRentalHistory(List<String> rentalHistory){
            this.rentalHistory = rentalHistory;
            return this;
        }

        public ActiveRentalModel build(){
            return new ActiveRentalModel(rentalID,rentalHistory);
        }
    }
}
