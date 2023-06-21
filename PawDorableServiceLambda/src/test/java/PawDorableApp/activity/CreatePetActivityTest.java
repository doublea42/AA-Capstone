package PawDorableApp.activity;

import PawDorableApp.activity.request.CreatePetRequest;
import PawDorableApp.activity.results.CreatePetResult;
import PawDorableApp.dynamodb.PetDao;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.models.Gender;
import PawDorableApp.models.KindOfPet;
import PawDorableApp.models.PetModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class CreatePetActivityTest {
    @Mock
    private PetDao petDao;
    @Mock
    private ProfileDao profileDao;

    private CreatePetActivity createPetActivity;

    @BeforeEach
    void setup(){
        openMocks(this);
        createPetActivity = new CreatePetActivity(petDao, profileDao);
    }


    @Test
    void handleRequest_validRequest_CreatePet() {
        //Given
        String name = "chocolo";
        String kindOfPet = "DOG";
        KindOfPet kindOfPetEnum = KindOfPet.DOG;
        String age = "5";
        int age5 = 5;
        String gender = "MALE";
        Gender genderEnum = Gender.MALE;
        String available = "true";
        String ownerEmail = "testMail@testMail.com";
        Set<String> newList = new HashSet<>();
        newList.add("NEW");


        CreatePetRequest request = CreatePetRequest.builder()
                .withName(name)
                .withKindOfPet(kindOfPet)
                .withAge(age)
                .withGender(gender)
                .withAvailable(available)
                .withOwnerEmail(ownerEmail)
                .build();

        //WHEN
        CreatePetResult result = createPetActivity.handleRequest(request);
        String id = result.getPet().getID();

        //THEN
        verify(petDao).saveNewPet(kindOfPet,name,ownerEmail,age, gender, available);

        PetModel petModel = new PetModel(id,kindOfPetEnum,name,ownerEmail,
                age5, genderEnum, newList,true);
        assertEquals(petModel, result.getPet());

    }
}