package PawDorableApp.dynamodb.models;

import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "ActiveRental")
public class ActiveRental {
    private String rentalID;
    private Set<String> rentalHistory;

    @DynamoDBHashKey(attributeName = "rentalID")
    public String getRentalID() {
        return rentalID;
    }

    @DynamoDBAttribute(attributeName = "rentalHistory")
    public Set<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    public void setRentalHistory(Set<String> rentalHistory) {
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
        return Objects.equals(rentalID, that.rentalID)
                && Objects.equals(rentalHistory, that.rentalHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalID, rentalHistory);
    }
}
