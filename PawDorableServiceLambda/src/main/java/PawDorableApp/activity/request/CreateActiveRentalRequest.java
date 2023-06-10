package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonDeserialize(builder = CreateActiveRentalRequest.Builder.class)
public class CreateActiveRentalRequest {
//    private final Logger log = LogManager.getLogger();

    private final String rentalHistory;

    public CreateActiveRentalRequest(String rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    public String getRentalHistory() {
        return rentalHistory;
    }

    @Override
    public String toString() {
        return "CreateActiveRentalRequest{" +
                "rentalHistory='" + rentalHistory + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String rentalHistory;

        public Builder withRentalHistory(String rentalHistory){
         this.rentalHistory = rentalHistory;
            return this;
        }

        public CreateActiveRentalRequest build(){ return new CreateActiveRentalRequest(rentalHistory); }

    }
}
