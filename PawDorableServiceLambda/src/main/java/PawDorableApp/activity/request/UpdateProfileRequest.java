package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonDeserialize(builder = UpdateProfileRequest.Builder.class)
public class UpdateProfileRequest {
//    private final Logger log = LogManager.getLogger();

    private final String emailAddress;
    private final String firstName;
    private final String lastName;
    private final String age;

    public UpdateProfileRequest(String emailAddress, String firstName, String lastName, String age) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "UpdateProfileRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String emailAddress;
        private String first;
        private String last;
        private String age;

        public  Builder withEmailAddress(String email){
            this.emailAddress = email;
            return this;
        }
        public Builder withFirstName(String first){
            this.first = first;
            return this;
        }
        public Builder withLastName(String last){
            this.last = last;
            return this;
        }
        public Builder withAge(String age){
            this.age = age;
            return this;
        }

        public UpdateProfileRequest build(){return new UpdateProfileRequest(emailAddress,first, last, age);}
    }
}
