package PawDorableApp.dynamodb.models;

import java.util.List;
import java.util.Objects;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;


@DynamoDBTable(tableName = "Profile")
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMyPets() {
        return myPets;
    }

    public void setMyPets(List<String> myPets) {
        this.myPets = myPets;
    }

    public List<String> getRental() {
        return rental;
    }

    public void setRental(List<String> rental) {
        this.rental = rental;
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(List<String> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    public List<String> getFavoriteRental() {
        return favoriteRental;
    }

    public void setFavoriteRental(List<String> favoriteRental) {
        this.favoriteRental = favoriteRental;
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
