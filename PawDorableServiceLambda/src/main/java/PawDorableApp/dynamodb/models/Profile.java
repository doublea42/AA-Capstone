package PawDorableApp.dynamodb.models;

import java.util.List;
import java.util.Objects;

import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "profile")
public class Profile {

    private  String emailAddress;
    private String ID;
    private String firstName;
    private String lastName;
    private int age;
    private List<String> myPets;
    private List<String> rental;
    private List<String> rentalHistory;
    private List<String> favoriteRental;

    @DynamoDBHashKey(attributeName = "ID")
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    @DynamoDBAttribute(attributeName = "emailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    @DynamoDBAttribute(attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @DynamoDBAttribute(attributeName = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @DynamoDBAttribute(attributeName = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @DynamoDBAttribute(attributeName = "myPets")
    public List<String> getMyPets() {
        return myPets;
    }

    public void setMyPets(List<String> myPets) {
        this.myPets = myPets;
    }
    @DynamoDBAttribute(attributeName = "rental")
    public List<String> getRental() {
        return rental;
    }

    public void setRental(List<String> rental) {
        this.rental = rental;
    }
    @DynamoDBAttribute(attributeName = "rentalHistory")
    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(List<String> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }
    @DynamoDBAttribute(attributeName = "favoriteRental")
    public List<String> getFavoriteRental() {
        return favoriteRental;
    }

    public void setFavoriteRental(List<String> favoriteRental) {
        this.favoriteRental = favoriteRental;
    }

    public String createId(){
        return PawDorableServiceUtils.generateId();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return age == profile.age && emailAddress.equals(profile.emailAddress)
                && ID.equals(profile.ID) && firstName.equals(profile.firstName)
                && lastName.equals(profile.lastName)
                && Objects.equals(myPets, profile.myPets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, ID, firstName, lastName, age, myPets);
    }
}
