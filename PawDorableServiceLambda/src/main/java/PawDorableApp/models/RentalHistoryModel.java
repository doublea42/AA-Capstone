package PawDorableApp.models;

import java.util.Objects;

public class RentalHistoryModel {
    private final String ID;
    private final String petID;
    private final String profileID;
    private final int timesRented;
    private final double score;

    public RentalHistoryModel(String historyID, String petID,
                              String profileID, int timesRented,
                              double score) {
        this.ID = historyID;
        this.petID = petID;
        this.profileID = profileID;
        this.timesRented = timesRented;
        this.score = score;
    }

    public String getID() {
        return ID;
    }

    public String getPetID() {
        return petID;
    }

    public String getProfileID() {
        return profileID;
    }

    public int getTimesRented() {
        return timesRented;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalHistoryModel that = (RentalHistoryModel) o;
        return timesRented == that.timesRented
                && Double.compare(that.score, score) == 0
                && Objects.equals(ID, that.ID)
                && Objects.equals(petID, that.petID)
                && Objects.equals(profileID, that.profileID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, petID, profileID, timesRented, score);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String ID;
        private String petID;
        private String profileID;
        private int timesRented;
        private double score;

        public Builder withHistoryID(String historyID){
            this.ID = historyID;
            return this;
        }
        public Builder withPetID(String petID){
            this.petID = petID;
            return this;
        }
        public Builder withProfileID(String profileID){
            this.profileID = profileID;
            return this;
        }
        public Builder withTimesRented(int timesRented){
            this.timesRented = timesRented;
            return this;
        }
        public Builder withScore(double score){
            this.score = score;
            return this;
        }

        public RentalHistoryModel build(){
            return new RentalHistoryModel(ID,petID,profileID,timesRented,score);
        }
    }

}
