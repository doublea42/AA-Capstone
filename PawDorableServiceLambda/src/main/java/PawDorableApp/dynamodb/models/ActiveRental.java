package PawDorableApp.dynamodb.models;

import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "activeRental")
public class ActiveRental {
    private String ID;
    private String rentalHistory;

    @DynamoDBHashKey(attributeName = "ID")
    public String getID() {
        return ID;
    }

    @DynamoDBAttribute(attributeName = "rentalHistory")
    public String getRentalHistory() {
        return rentalHistory;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setRentalHistory(String rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    public String createId(){
        return PawDorableServiceUtils.generateId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveRental that = (ActiveRental) o;
        return Objects.equals(ID, that.ID)
                && Objects.equals(rentalHistory, that.rentalHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, rentalHistory);
    }
}
