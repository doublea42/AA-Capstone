package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreatePetRequest.Builder.class)
public class CreatePetRequest {
    private final String name;
    private final String kind;
    private final String age;
    private final String gender;
    private final String available;
    private final String ownerEmail;


    public CreatePetRequest(String name, String kind, String age, String gender, String available, String ownerEmail) {
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.gender = gender;
        this.available = available;
        this.ownerEmail = ownerEmail;
    }


    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAvailable() {
        return available;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    @Override
    public String toString() {
        return "CreatePetRequest{" +
                "name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", available='" + available + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}

    @JsonPOJOBuilder
    public static class Builder{
        private String name;
        private String ownerEmail;
        private String kind;
        private String age;
        private String gender;
        private String available;


        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withOwnerEmail(String email){
            this.ownerEmail = email;
            return this;
        }
        public Builder withKind(String kind){
            this.kind = kind;
            return this;
        }
        public Builder withAge(String age){
            this.age = age;
            return this;
        }
        public Builder withGender(String gender){
            this.gender = gender;
            return this;
        }
        public Builder withAvailable(String available){
            this.available = available;
            return this;
        }

        public CreatePetRequest build() {return new CreatePetRequest(name,ownerEmail,kind,age,gender,available);}

    }
}
