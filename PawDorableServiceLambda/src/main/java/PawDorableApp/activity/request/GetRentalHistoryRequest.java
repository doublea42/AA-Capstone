package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetRentalHistoryRequest.Builder.class)
public class GetRentalHistoryRequest {
//    private final Logger log = LogManager.getLogger();

    private final String id;

    public GetRentalHistoryRequest(String historyID) {
        this.id = historyID;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GetRentalRequest{" +
                "historyID='" + id + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String id;

        public Builder withHistoryID(String id){
            this.id = id;
            return this;
        }
        public GetRentalHistoryRequest build(){return new GetRentalHistoryRequest(id);}
    }
}
