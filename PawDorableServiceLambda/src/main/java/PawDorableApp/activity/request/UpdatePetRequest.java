package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdatePetRequest.Builder.class)
public class UpdatePetRequest {
    private final String ID;
    private final  String age;
    private final String available;

    public UpdatePetRequest(String ID, String age, String available) {
        this.ID = ID;
        this.age = age;
        this.available = available;
    }

    public String getID() {
        return ID;
    }

    public String getAge() {
        return age;
    }

    public String getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "UpdatePetRequest{" +
                "ID='" + ID + '\'' +
                ", age='" + age + '\'' +
                ", available='" + available + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private  String ID;
        private String age;
        private String available;
        public Builder withID(String ID){
            this.ID = ID;
            return this;
        }
        public Builder withAge(String age){
            this.age = age;
            return this;
        }
        public Builder withAvailable(String available){
            this.available = available;
            return this;
        }
        public UpdatePetRequest build(){return new UpdatePetRequest(ID, age, available);}
    }
}
