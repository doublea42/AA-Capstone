package PawDorableApp.converter;


import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.models.PetModel;
import PawDorableApp.models.ProfileModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelConverter {
    private final Logger log = LogManager.getLogger();

    public ProfileModel toProfileModel(Profile profile){

        return ProfileModel.builder()
                .withID(profile.getID())
                .withEmailAddress(profile.getEmailAddress())
                .withFirstName(profile.getFirstName())
                .withLastName(profile.getLastName())
                .withAge(profile.getAge())
                .withMyPets(profile.getMyPets())
                .withRental(profile.getRental())
                .withRentalHistory(profile.getRentalHistory())
                .withFavoriteRental(profile.getFavoriteRental())
                .build();
    }

    public PetModel toPetModel(Pet pet){
        return PetModel.builder()
                .withID(pet.getID())
                .withKindOfPet(pet.getKindOfPet())
                .withPetName(pet.getName())
                .withOwnerEmail(pet.getOwnerEmail())
                .withAge(pet.getAge())
                .withGender(pet.getGender())
                .withRentalHistory(pet.getRentalHistory())
                .withAvailable(pet.isAvailable())
                .build();
    }

}
