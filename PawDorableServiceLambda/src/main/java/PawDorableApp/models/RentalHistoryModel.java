package PawDorableApp.models;

import java.util.Objects;

public class RentalHistoryModel {
    private final String historyID;
    private final String petID;
    private final String profileID;
    private final int timesRented;
    private final double score;

    public RentalHistoryModel(String historyID, String petID,
                              String profileID, int timesRented,
                              double score) {
        this.historyID = historyID;
        this.petID = petID;
        this.profileID = profileID;
        this.timesRented = timesRented;
        this.score = score;
    }

    public String getHistoryID() {
        return historyID;
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
                && Objects.equals(historyID, that.historyID)
                && Objects.equals(petID, that.petID)
                && Objects.equals(profileID, that.profileID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyID, petID, profileID, timesRented, score);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String historyID;
        private String petID;
        private String profileID;
        private int timesRented;
        private double score;

        public Builder withHistoryID(String historyID){
            this.historyID = historyID;
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
            return new RentalHistoryModel(historyID,petID,profileID,timesRented,score);
        }
    }

}
