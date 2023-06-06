package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@JsonDeserialize(builder = CreateProfileRequest.Builder.class)
public class CreateProfileRequest {
    private final Logger log = LogManager.getLogger();
    // {"email": "jhon@notARealMail.com", "first": "Jhon", "last": "Smith", "age": "35"}

    private final String emailAddress;
    private final String firstName;
    private final String lastName;
    private final String age;

    private CreateProfileRequest(String email, String first, String last, String age) {
        this.emailAddress = email;
        this.firstName = first;
        this.lastName = last;
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

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    public String toString(String email, String first, String last, String age) {
        return "CreateProfileRequest{" +
                "email= " + email + '\'' +
                "first= " + first + '\'' +
                "last= " + last + '\'' +
                "age= " + age + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{

        private String emailAddress;
        private String first;
        private String last;
        private String age;

        public Builder withEmailAddress(String email){
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

        public CreateProfileRequest build(){
            return new CreateProfileRequest(emailAddress,first,last,age);
        }

    }

}
