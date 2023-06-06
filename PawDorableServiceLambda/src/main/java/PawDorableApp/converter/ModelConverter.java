package PawDorableApp.converter;


import PawDorableApp.dynamodb.models.Profile;
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

}
