package PawDorableApp.models;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProfileModel {

    private final String emailAddress;
    private final String ID;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final Set<String> myPets;
    private final Set<String> rental;
    private final Set<String> rentalHistory;
    private final Set<String> favoriteRental;

    public ProfileModel(String emailAddress, String ID, String firstName,
                        String lastName, int age, Set<String> myPets,
                        Set<String> rental, Set<String> rentalHistory,
                        Set<String> favoriteRental) {

        this.emailAddress = emailAddress;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.myPets = myPets;
        this.rental = rental;
        this.rentalHistory = rentalHistory;
        this.favoriteRental = favoriteRental;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Set<String> getMyPets() {
        return myPets;
    }

    public Set<String> getRental() {
        return rental;
    }

    public Set<String> getRentalHistory() {
        return rentalHistory;
    }

    public Set<String> getFavoriteRental() {
        return favoriteRental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileModel that = (ProfileModel) o;
        return age == that.age
                && Objects.equals(emailAddress, that.emailAddress)
                && Objects.equals(ID, that.ID)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(myPets, that.myPets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, ID, firstName, lastName, age, myPets);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private  String emailAddress;
        private String ID;
        private String firstName;
        private String lastName;
        private int age;
        private Set<String> myPets;
        private Set<String> rental;
        private Set<String> rentalHistory;
        private Set<String> favoriteRental;

        public Builder withEmailAddress(String emailAddress){
            this.emailAddress = emailAddress;
            return this;
        }
        public Builder withID(String ID){
            this.ID = ID;
            return this;
        }
        public Builder withFirstName(String first){
            this.firstName = first;
            return this;
        }
        public Builder withLastName(String last){
            this.lastName = last;
            return this;
        }
        public Builder withAge(int age){
            this.age = age;
            return this;
        }
        public Builder withMyPets(Set<String> pets){
            this.myPets = pets;
            return this;
        }
        public Builder withRental(Set<String> pets){
            this.rental = pets;
            return this;
        }
        public Builder withRentalHistory(Set<String> pets){
            this.rentalHistory = pets;
            return this;
        }
        public Builder withFavoriteRental(Set<String> pets){
            this.favoriteRental = pets;
            return this;
        }

        public ProfileModel build(){
            return new ProfileModel(emailAddress,ID,firstName,lastName,age,
                    myPets,rental,rentalHistory,favoriteRental);
        }
    }


}
