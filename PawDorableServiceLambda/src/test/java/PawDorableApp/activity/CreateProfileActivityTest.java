package PawDorableApp.activity;

import PawDorableApp.activity.request.CreateProfileRequest;
import PawDorableApp.activity.results.CreateProfileResult;
import PawDorableApp.dynamodb.ProfileDao;
import PawDorableApp.dynamodb.models.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateProfileActivityTest {
    @Mock
    private ProfileDao profileDao;

    private CreateProfileActivity createProfileActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createProfileActivity = new CreateProfileActivity(profileDao);
    }

    @Test
    void handleRequest_validRequest_CreateProfile() {
        //GIVEN
        String name = "Jhon";
        String last = "Smith";
        String age = "25";
        String email = "jhon@notEmail.com";

        CreateProfileRequest request = CreateProfileRequest.builder()
                .withFirstName(name)
                .withLastName(last)
                .withAge(age)
                .build();

        Profile savedProfile = new Profile();
        savedProfile.setFirstName(name);
        savedProfile.setLastName(last);
        savedProfile.setAge(25);
        savedProfile.setEmailAddress(email);

        //WHEN
        CreateProfileResult result = createProfileActivity.handleRequest(request);
        //THEN
        profileDao.saveNewProfile(email,name,last,age);

        assertNotNull(result.getProfile().getEmailAddress());
        assertEquals(savedProfile.getFirstName(), result.getProfile().getFirstName());
        assertEquals(savedProfile.getLastName(), result.getProfile().getLastName());
        assertEquals(savedProfile.getAge(), result.getProfile().getAge());
        assertEquals(savedProfile.getEmailAddress(), result.getProfile().getEmailAddress());
    }
}