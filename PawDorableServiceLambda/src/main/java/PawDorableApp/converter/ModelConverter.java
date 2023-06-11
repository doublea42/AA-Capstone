package PawDorableApp.converter;


import PawDorableApp.dynamodb.models.ActiveRental;
import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.models.ActiveRentalModel;
import PawDorableApp.models.PetModel;
import PawDorableApp.models.ProfileModel;
import PawDorableApp.models.RentalHistoryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelConverter {
    private final Logger log = LogManager.getLogger();

    public ProfileModel toProfileModel(Profile profile){

        return ProfileModel.builder()
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

    public ActiveRentalModel toActiveRentalModel(ActiveRental activeRental){
        return ActiveRentalModel.builder()
                .withRentalID(activeRental.getRentalID())
                .withRentalHistory(activeRental.getRentalHistory())
                .build();
    }

    public RentalHistoryModel toRentalHistoryModel(RentalHistory rental) {
        return RentalHistoryModel.builder()
                .withHistoryID(rental.getHistoryID())
                .withPetID(rental.getPetID())
                .withProfileID(rental.getProfileID())
                .withTimesRented(rental.getTimesRented())
                .withScore(rental.getScore())
                .build();
    }

}
