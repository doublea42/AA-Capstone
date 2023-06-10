package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonDeserialize(builder = GetPetRequest.Builder.class)
public class GetPetRequest {
//    private final Logger log = LogManager.getLogger();

    private final String ID;

    public GetPetRequest(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "GetPetRequest{" +
                "ID='" + ID + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder() ;}
    @JsonPOJOBuilder
    public static class Builder{
        private  String ID;

        public Builder withID(String ID){
            this.ID = ID;
            return this;
        }

        public GetPetRequest build(){return new GetPetRequest(ID);}

    }
}
