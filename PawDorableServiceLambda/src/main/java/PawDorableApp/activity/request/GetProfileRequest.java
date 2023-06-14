package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonDeserialize(builder = GetProfileRequest.Builder.class)
public class GetProfileRequest {
//    private final Logger log = LogManager.getLogger();

    private final String emailAddress;

    public GetProfileRequest(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "GetProfileRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{

        private String emailAddress;

        public Builder withEmailAddress(String email) {
            this.emailAddress = email;
            return this;
        }
        public GetProfileRequest build(){return new GetProfileRequest(emailAddress);}
    }
}
