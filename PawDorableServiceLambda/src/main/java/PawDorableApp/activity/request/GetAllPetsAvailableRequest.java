package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetAllPetsAvailableRequest.Builder.class)
public class GetAllPetsAvailableRequest {
    private final String profilesID;

    public GetAllPetsAvailableRequest(String profilesID) {
        this.profilesID = profilesID;
    }

    public String getProfilesID() {
        return profilesID;
    }

    @Override
    public String toString() {
        return "GetAllPetsAvailableRequest{" +
                "profilesID='" + profilesID + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder() ;}
    @JsonPOJOBuilder
    public static class Builder{
        private String profilesID;

        public Builder withProfileID(String ID){
            this.profilesID = ID;
            return this;
        }

        public GetAllPetsAvailableRequest build(){
            return new GetAllPetsAvailableRequest(profilesID);
        }

    }
}
