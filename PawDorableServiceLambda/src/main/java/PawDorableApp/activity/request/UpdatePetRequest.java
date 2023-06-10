package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdatePetRequest.Builder.class)
public class UpdatePetRequest {
    private final  String age;
    private final String available;

    public UpdatePetRequest(String age, String available) {
        this.age = age;
        this.available = available;
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
                "age='" + age + '\'' +
                ", available='" + available + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String age;
        private String available;
        public Builder withAge(String age){
            this.age = age;
            return this;
        }
        public Builder withAvailable(String available){
            this.available = available;
            return this;
        }
        public UpdatePetRequest build(){return new UpdatePetRequest(age,available);}
    }
}
