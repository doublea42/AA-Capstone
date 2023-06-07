package PawDorableApp.activity.results;

import PawDorableApp.models.PetModel;

public class CreatePetResult {
    private final PetModel pet;

    public CreatePetResult(PetModel pet) {
        this.pet = pet;
    }

    public PetModel getPet(){
        return pet;
    }

    @Override
    public String toString() {
        return "CreatePetResult{" +
                "pet=" + pet +
                '}';
    }

    public static Builder builder(){return new Builder();}
    public static class Builder{

        private PetModel pet;

        public Builder withPet(PetModel pet){
            this.pet = pet;
            return this;
        }
        public CreatePetResult build(){return new CreatePetResult(pet);}

    }
}
