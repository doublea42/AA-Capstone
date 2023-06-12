package PawDorableApp.models;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PetModel {

    private final String ID;
    private final KindOfPet kindOfPet;
    private final String name;
    private final String ownerEmail;
    private final int age;
    private final Gender gender;
    private final Set<String> rentalHistory;
    private final Boolean available;

    public PetModel(String ID, KindOfPet kindOfPet, String name,
                    String ownerID, int age, Gender gender,
                    Set<String> rentalHistory, boolean available) {
        this.ID = ID;
        this.kindOfPet = kindOfPet;
        this.name = name;
        this.ownerEmail = ownerID;
        this.age = age;
        this.gender = gender;
        this.rentalHistory = rentalHistory;
        this.available = available;
    }

    public String getID() {
        return ID;
    }

    public KindOfPet getKindOfPet() {
        return kindOfPet;
    }

    public String getName() {
        return name;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public int getAge() {
        return age;
    }

    public Enum<Gender> getGender() {
        return gender;
    }

    public Set<String> getRentalHistory() {
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
                && Objects.equals(ownerEmail, petModel.ownerEmail)
                && Objects.equals(gender, petModel.gender)
                && Objects.equals(rentalHistory, petModel.rentalHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, kindOfPet, name, ownerEmail, age, gender, rentalHistory, available);
    }

    public static Builder builder(){return  new Builder();}

    public static class Builder{
        private String ID;
        private KindOfPet kindOfPet;
        private String petName;
        private String ownerEmail;
        private int age;
        private Gender gender;
        private Set<String> rentalHistory;
        private Boolean available;

        public Builder withID(String id){
            this.ID = id;
            return this;
        }
        public Builder withKindOfPet(KindOfPet kind){
            this.kindOfPet = kind;
            return this;
        }
        public Builder withPetName(String name){
            this.petName = name;
            return this;
        }
        public Builder withOwnerEmail(String owner){
            this.ownerEmail = owner;
            return this;
        }
        public Builder withAge(int age){
            this.age = age;
            return this;
        }
        public Builder withGender(Gender gender){
            this.gender = gender;
            return this;
        }
        public Builder withRentalHistory(Set<String> rentalHistory){
            this.rentalHistory = rentalHistory;
            return this;
        }
        public Builder withAvailable(boolean available){
            this.available = available;
            return this;
        }
        public PetModel build(){
            return new PetModel(ID,kindOfPet,petName,ownerEmail,age,gender,rentalHistory,available);
        }
    }

}
