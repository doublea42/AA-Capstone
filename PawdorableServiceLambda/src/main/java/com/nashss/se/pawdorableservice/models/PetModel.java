package src.main.java.com.nashss.se.pawdorableservice.models;

import java.util.List;
import java.util.Objects;

public class PetModel {

    private final String ID;
    private final Enum<KindOfPet> kindOfPet;
    private final String name;
    private final String ownerID;
    private final int age;
    private final Enum<Gender> gender;
    private final List<String> rentalHistory;
    private final boolean available;

    public PetModel(String ID, Enum<KindOfPet> kindOfPet, String name,
                    String ownerID, int age, Enum<Gender> gender,
                    List<String> rentalHistory, boolean available) {
        this.ID = ID;
        this.kindOfPet = kindOfPet;
        this.name = name;
        this.ownerID = ownerID;
        this.age = age;
        this.gender = gender;
        this.rentalHistory = rentalHistory;
        this.available = available;
    }

    public String getID() {
        return ID;
    }

    public Enum<KindOfPet> getKindOfPet() {
        return kindOfPet;
    }

    public String getName() {
        return name;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public int getAge() {
        return age;
    }

    public Enum<Gender> getGender() {
        return gender;
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetModel petModel = (PetModel) o;
        return age == petModel.age
                && available == petModel.available
                && Objects.equals(ID, petModel.ID)
                && Objects.equals(kindOfPet, petModel.kindOfPet)
                && Objects.equals(name, petModel.name)
                && Objects.equals(ownerID, petModel.ownerID)
                && Objects.equals(gender, petModel.gender)
                && Objects.equals(rentalHistory, petModel.rentalHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, kindOfPet, name, ownerID, age, gender, rentalHistory, available);
    }
    
    public static Builder builder(){return  new Builder();}

    public static class Builder{
        private String ID;
        private Enum<KindOfPet> kindOfPet;
        private String petName;
        private String ownerID;
        private int age;
        private Enum<Gender> gender;
        private List<String> rentalHistory;
        private boolean available;

        public Builder withID(String id){
            this.ID = id;
            return this;
        }
        public Builder withKindOfPet(Enum<KindOfPet> kind){
            this.kindOfPet = kind;
            return this;
        }
        public Builder withPetName(String name){
            this.petName = name;
            return this;
        }
        public Builder withOwnerID(String owner){
            this.ownerID = owner;
            return this;
        }
        public Builder withAge(int age){
            this.age = age;
            return this;
        }
        public Builder withGender(Enum<Gender> gender){
            this.gender = gender;
            return this;
        }
        public Builder withRentalHistory(List<String> rentalHistory){
            this.rentalHistory = rentalHistory;
            return this;
        }
        public Builder withAvailable(boolean available){
            this.available = available;
            return this;
        }
        public PetModel build(){
            return new PetModel(ID,kindOfPet,petName,ownerID,age,gender,rentalHistory,available);
        }
    }

}
