package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonDeserialize(builder = CreateActiveRentalRequest.Builder.class)
public class CreateActiveRentalRequest {
//    private final Logger log = LogManager.getLogger();

    private final String petID;
    private final String profileID;

    public CreateActiveRentalRequest(String petID, String profileID) {
        this.petID = petID;
        this.profileID = profileID;
    }

    public String getPetID() {
        return petID;
    }

    public String getProfileID() {
        return profileID;
    }

    @Override
    public String toString() {
        return "CreateActiveRentalRequest{" +
                "petID='" + petID + '\'' +
                ", profileID='" + profileID + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String petID;
        private String profileID;

        public Builder withPetID(String petID){
            this.petID = petID;
            return this;
        }
        public Builder withProfileID(String profileID){
            this.profileID = profileID;
            return this;
        }

        public CreateActiveRentalRequest build(){ return new CreateActiveRentalRequest(petID, profileID); }

    }
}
