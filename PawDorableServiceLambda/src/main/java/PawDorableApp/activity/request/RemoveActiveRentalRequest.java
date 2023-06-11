package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class RemoveActiveRentalRequest {
    private final String rentalID;
    private final String score;

    public RemoveActiveRentalRequest(String rentalID, String score) {
        this.rentalID = rentalID;
        this.score = score;
    }

    public String getRentalID() {
        return rentalID;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "RemoveActiveRentalRequest{" +
                "rentalID='" + rentalID + '\'' +
                ", score=" + score +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String rentalID;
        private String score;
        public Builder withRentalID(String id){
            this.rentalID = id;
            return this;
        }
        public Builder withRentalScore(String score){
            this.score = score;
            return this;
        }
        public RemoveActiveRentalRequest build(){return new RemoveActiveRentalRequest(rentalID, score);}
    }
}
