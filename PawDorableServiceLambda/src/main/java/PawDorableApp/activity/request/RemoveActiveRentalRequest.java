package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class RemoveActiveRentalRequest {
    private final String rentalID;

    public RemoveActiveRentalRequest(String rentalID) {
        this.rentalID = rentalID;
    }

    public String getRentalID() {
        return rentalID;
    }

    @Override
    public String toString() {
        return "RemoveActiveRentalRequest{" +
                "rentalID='" + rentalID + '\'' +
                '}';
    }
    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String rentalID;
        public Builder withRentalID(String id){
            this.rentalID = id;
            return this;
        }
        public RemoveActiveRentalRequest build(){return new RemoveActiveRentalRequest(rentalID);}
    }
}
