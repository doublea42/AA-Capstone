package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RemovePetRequest.Builder.class)
public class RemovePetRequest {
    private final String ID;

    public RemovePetRequest(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "RemovePetRequest{" +
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
        public RemovePetRequest build(){return new RemovePetRequest(ID);}
    }
}
