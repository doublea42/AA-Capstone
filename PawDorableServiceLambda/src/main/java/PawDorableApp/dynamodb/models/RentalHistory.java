package PawDorableApp.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "RentalHistory")
public class RentalHistory {
    private String historyID;
    private String petID;
    private String profileID;
    private int timesRented;
    private double score;

    @DynamoDBHashKey(attributeName = "historyID")
    public String getHistoryID() {
        return historyID;
    }

    @DynamoDBAttribute(attributeName = "petID")
    public String getPetID() {
        return petID;
    }

    @DynamoDBAttribute(attributeName = "profileID")
    public String getProfileID() {
        return profileID;
    }

    @DynamoDBAttribute(attributeName = "timesRented")
    public int getTimesRented() {
        return timesRented;
    }

    @DynamoDBAttribute(attributeName = "score")
    public double getScore() {
        return score;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public void setTimesRented(int timesRented) {
        this.timesRented = timesRented;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalHistory that = (RentalHistory) o;
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
}
