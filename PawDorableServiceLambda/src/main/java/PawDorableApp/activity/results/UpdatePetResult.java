package PawDorableApp.activity.results;

import PawDorableApp.models.PetModel;

public class UpdatePetResult {
    private final PetModel pet;

    public UpdatePetResult(PetModel pet) {
        this.pet = pet;
    }

    public PetModel getPet() {
        return pet;
    }

    @Override
    public String toString() {
        return "UpdatePetResult{" +
                "pet=" + pet +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }
    public  static class  Builder{
        private PetModel pet;
        public Builder withPetModel(PetModel pet){
            this.pet = pet;
            return this;
        }
        public UpdatePetResult build() {
            return new UpdatePetResult(pet);
        }
    }
}
