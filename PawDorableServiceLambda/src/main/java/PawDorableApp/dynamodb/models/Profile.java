package PawDorableApp.dynamodb.models;

import java.util.Objects;
import java.util.Set;

import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "profile")
public class Profile {

    private  String emailAddress;
    private String firstName;
    private String lastName;
    private int age;
    private Set<String> myPets;
    private Set<String> rental;
    private Set<String> rentalHistory;
    private Set<String> favoriteRental;

    @DynamoDBHashKey(attributeName = "emailAddress")
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
    public Set<String> getMyPets() {
        return myPets;
    }

    public void setMyPets(Set<String> myPets) {
        this.myPets = myPets;
    }
    @DynamoDBAttribute(attributeName = "rental")
    public Set<String> getRental() {
        return rental;
    }

    public void setRental(Set<String> rental) {
        this.rental = rental;
    }
    @DynamoDBAttribute(attributeName = "rentalHistory")
    public Set<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(Set<String> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }
    @DynamoDBAttribute(attributeName = "favoriteRental")
    public Set<String> getFavoriteRental() {
        return favoriteRental;
    }

    public void setFavoriteRental(Set<String> favoriteRental) {
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
                && lastName.equals(profile.lastName)
                && Objects.equals(myPets, profile.myPets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, firstName, lastName, age, myPets);
    }
}
