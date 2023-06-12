package PawDorableApp.dynamodb.models;



import PawDorableApp.models.Gender;
import PawDorableApp.models.KindOfPet;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "pet")
public class Pet {
    private String ID;
    private KindOfPet kindOfPet;
    private String name;
    private String ownerEmail;
    private int age;
    private Gender gender;
    private Set<String> rentalHistory;
    private Boolean available;

    @DynamoDBHashKey(attributeName = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "kindOfPet")
    public KindOfPet getKindOfPet() {
        return kindOfPet;
    }

    public void setKindOfPet(KindOfPet kindOfPet) {
        this.kindOfPet = kindOfPet;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "ownerEmail")
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @DynamoDBAttribute(attributeName = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @DynamoDBAttribute(attributeName = "rentalHistory")
    public Set<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(Set<String> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    @DynamoDBAttribute(attributeName = "available")
    public Boolean isAvailable() {
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
                && Objects.equals(ownerEmail, pet.ownerEmail)
                && Objects.equals(gender, pet.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, kindOfPet, name, ownerEmail, age, gender, available);
    }
}
