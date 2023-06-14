package PawDorableApp.models;

import java.util.Objects;

public class ActiveRentalModel {
    private final String ID;
    private final String rentalHistory;

    public ActiveRentalModel(String rentalID, String rentalHistory) {
        this.ID = rentalID;
        this.rentalHistory = rentalHistory;
    }

    public String getID() {
        return ID;
    }

    public String getRentalHistory() {
        return rentalHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveRentalModel that = (ActiveRentalModel) o;
        return Objects.equals(ID, that.ID)
                && Objects.equals(rentalHistory, that.rentalHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, rentalHistory);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String ID;
        private String rentalHistory;

        public Builder withRentalID(String id){
            this.ID = id;
            return this;
        }
        public Builder withRentalHistory(String rentalHistory){
            this.rentalHistory = rentalHistory;
            return this;
        }

        public ActiveRentalModel build(){
            return new ActiveRentalModel(ID,rentalHistory);
        }
    }
}
