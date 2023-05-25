package PawDorableApp.dynamodb.models;



import PawDorableApp.models.Gender;
import PawDorableApp.models.KindOfPet;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "Pet")
public class Pet {
    private String ID;
    private Enum<KindOfPet> kindOfPet;
    private String name;
    private String ownerID;
    private int age;
    private Enum<Gender> gender;
    private List<String> rentalHistory;
    private boolean available;

    @DynamoDBHashKey(attributeName = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    @DynamoDBAttribute(attributeName = "kindOfPet")
    public Enum<KindOfPet> getKindOfPet() {
        return kindOfPet;
    }

    public void setKindOfPet(Enum<KindOfPet> kindOfPet) {
        this.kindOfPet = kindOfPet;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "ownerID")
    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    @DynamoDBAttribute(attributeName = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @DynamoDBAttribute(attributeName = "gender")
    public Enum<Gender> getGender() {
        return gender;
    }

    public void setGender(Enum<Gender> gender) {
        this.gender = gender;
    }

    @DynamoDBAttribute(attributeName = "rentalHistory")
    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(List<String> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    @DynamoDBAttribute(attributeName = "available")
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && available == pet.available
                && Objects.equals(ID, pet.ID)
                && Objects.equals(kindOfPet, pet.kindOfPet)
                && Objects.equals(name, pet.name)
                && Objects.equals(ownerID, pet.ownerID)
                && Objects.equals(gender, pet.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, kindOfPet, name, ownerID, age, gender, available);
    }
}
