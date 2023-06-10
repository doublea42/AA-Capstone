package PawDorableApp.activity.request;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetActiveRentalRequest.Builder.class)
public class GetActiveRentalRequest {
    private final String rentalID;

    public GetActiveRentalRequest(String rentalID) {
        this.rentalID = rentalID;
    }

    public String getRentalID() {
        return rentalID;
    }

    @Override
    public String toString() {
        return "GetActiveRentalRequest{" +
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
        public GetActiveRentalRequest build(){return new GetActiveRentalRequest(rentalID);}
    }
}
