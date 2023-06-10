package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateRentalRequest.Builder.class)
public class UpdateRentalRequest {
    private final String score;

    public UpdateRentalRequest(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UpdateRentalRequest{" +
                "score='" + score + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String score;
        public Builder withScore(String score){
            this.score = score;
            return this;
        }
        public UpdateRentalRequest build(){return new UpdateRentalRequest(score);}
    }

}
