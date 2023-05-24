package PawDorableApp.dynamodb.models;

import src.main.java.com.nashss.se.pawdorableservice.models.Gender;
import src.main.java.com.nashss.se.pawdorableservice.models.KindOfPet;

import java.util.List;
import java.util.Objects;

public class Pet {
    private String ID;
    private Enum<KindOfPet> kindOfPet;
    private String name;
    private String ownerID;
    private int age;
    private Enum<Gender> gender;
    private List<String> rentalHistory;
    private boolean available;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Enum<KindOfPet> getKindOfPet() {
        return kindOfPet;
    }

    public void setKindOfPet(Enum<KindOfPet> kindOfPet) {
        this.kindOfPet = kindOfPet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Enum<Gender> getGender() {
        return gender;
    }

    public void setGender(Enum<Gender> gender) {
        this.gender = gender;
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(List<String> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

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
