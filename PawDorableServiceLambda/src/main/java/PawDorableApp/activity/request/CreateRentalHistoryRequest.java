package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonDeserialize(builder = CreateRentalHistoryRequest.Builder.class)
public class CreateRentalHistoryRequest {
//    private final Logger log = LogManager.getLogger();

    private final String petID;
    private final String profileID;

    public CreateRentalHistoryRequest(String petID, String profileID) {
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
        return "CreateRentalHistoryRequest{" +
                "petID='" + petID + '\'' +
                ", profileID='" + profileID + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private  String petID;
        private  String profileID;

        public Builder withPetID(String petID){
            this.petID = petID;
            return this;
        }
        public Builder withProfileID(String profileID){
            this.profileID = profileID;
            return this;
        }
        public CreateRentalHistoryRequest build() {
            return new CreateRentalHistoryRequest(petID,profileID);
        }

    }

}
