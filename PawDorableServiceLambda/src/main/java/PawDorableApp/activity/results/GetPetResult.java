package PawDorableApp.activity.results;

import PawDorableApp.models.PetModel;
import PawDorableApp.models.ProfileModel;

public class GetPetResult {
    private final PetModel pet;

    public GetPetResult(PetModel pet) {
        this.pet = pet;
    }

    public PetModel getPet() {
        return pet;
    }

    @Override
    public String toString() {
        return "GetPetResult{" +
                "pet=" + pet +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder{
        private PetModel pet;

        public Builder withPet(PetModel pet){
            this.pet = pet;
            return this;
        }

        public GetPetResult build(){return new GetPetResult(pet);}
    }
}
