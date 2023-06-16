package PawDorableApp.dynamodb.models;

import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "rentalHistory")
public class RentalHistory {
    private String ID;
    private String petID;
    private String profileID;
    private int timesRented;
    private double score;

    @DynamoDBHashKey(attributeName = "ID")
    public String getID() {
        return ID;
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

    public void setID(String ID) {
        this.ID = ID;
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

    public String createId(){
        return PawDorableServiceUtils.generateId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalHistory that = (RentalHistory) o;
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
}
