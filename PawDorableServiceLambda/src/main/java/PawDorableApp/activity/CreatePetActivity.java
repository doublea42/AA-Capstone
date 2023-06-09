package PawDorableApp.activity;

import PawDorableApp.activity.request.CreatePetRequest;
import PawDorableApp.activity.results.CreatePetResult;
import PawDorableApp.converter.ModelConverter;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.models.Pet;
import PawDorableApp.dynamodb.models.Profile;
import PawDorableApp.exceptions.PetInvalidValuesException;
import PawDorableApp.models.PetModel;
import PawDorableApp.utils.PawDorableServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class CreatePetActivity {
    private final Logger log = LogManager.getLogger();
    private final PetDao petDao;
    private final ProfileDao profileDao;

    @Inject
    public CreatePetActivity(PetDao petDao, ProfileDao profileDao) {
        this.petDao = petDao;
        this.profileDao = profileDao;
    }


    public CreatePetResult handleRequest(final CreatePetRequest createPetRequest){
        log.info("Received CreateRequest{}", createPetRequest);

        if(!PawDorableServiceUtils.isValidString(createPetRequest.getName())){
            throw new PetInvalidValuesException("Your Name cannot contain illegal characters");
        }
//        log.info("here <-----------------------{}-----------", createPetRequest);
        Pet newPet = petDao.savePet(true,"", createPetRequest.getKind(), createPetRequest.getName(),
                createPetRequest.getOwnerEmail(), createPetRequest.getAge(), createPetRequest.getGender(),
                null, Boolean.parseBoolean(createPetRequest.getAvailable()));


        log.info("here <------------------------------------");
        Profile tempPetOwner = profileDao.getPofile(createPetRequest.getOwnerEmail());
        log.info("here <------------------{}------------------", tempPetOwner);

        Set<String> newPetList = new HashSet<>();
        newPetList.add(newPet.getID());
        log.info("here <------------------------------------");
        profileDao.saveProfile(false, tempPetOwner.getID(), tempPetOwner.getEmailAddress(),
                tempPetOwner.getFirstName(), tempPetOwner.getLastName(), String.valueOf(tempPetOwner.getAge()),
                newPetList, tempPetOwner.getRental(),
                tempPetOwner.getRentalHistory(), tempPetOwner.getFavoriteRental());

        PetModel petModel = new ModelConverter().toPetModel(newPet);
        log.info("here <------------------------------------");


        return CreatePetResult.builder().withPet(petModel).build();
    }


}
