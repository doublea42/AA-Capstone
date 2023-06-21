package PawDorableApp.activity.results;

import PawDorableApp.dynamodb.models.Pet;

import java.util.List;

public class GetAllPetsAvailableResult {
    private final List<Pet> listOfPets;

    public GetAllPetsAvailableResult(List<Pet> listOfPets) {
        this.listOfPets = listOfPets;
    }

    public List<Pet> getListOfPets() {
        return listOfPets;
    }

    @Override
    public String toString() {
        return "GetAllPetsAvailableResult{" +
                "listOfPets=" + listOfPets +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder {
        private List<Pet> listOfPets;

        public Builder withListOfPets(List<Pet> list){
            this.listOfPets = list;
            return this;
        }

        public GetAllPetsAvailableResult build(){return new GetAllPetsAvailableResult(listOfPets);}

    }
}

