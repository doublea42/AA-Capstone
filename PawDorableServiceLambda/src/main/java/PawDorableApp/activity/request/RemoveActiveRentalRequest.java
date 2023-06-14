package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RemoveActiveRentalRequest.Builder.class)
public class RemoveActiveRentalRequest {
    private final String petID;
    private final String profileID;
    private final String score;

    public RemoveActiveRentalRequest(String petID, String profileID, String score) {
        this.petID = petID;
        this.profileID = profileID;
        this.score = score;
    }

    public String getPetID() {
        return petID;
    }

    public String getProfileID() {
        return profileID;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "RemoveActiveRentalRequest{" +
                "petID='" + petID + '\'' +
                ", profileID='" + profileID + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String petID;
        private String profileID;
        private String score;
        public Builder withPetID(String petID){
            this.petID = petID;
            return this;
        }public Builder withProfileID(String profileID){
            this.profileID = profileID;
            return this;
        }
        public Builder withRentalScore(String score){
            this.score = score;
            return this;
        }
        public RemoveActiveRentalRequest build(){return new RemoveActiveRentalRequest(petID, profileID, score);}
    }
}
