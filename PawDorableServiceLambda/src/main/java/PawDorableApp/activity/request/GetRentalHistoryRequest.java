package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetRentalHistoryRequest.Builder.class)
public class GetRentalHistoryRequest {
//    private final Logger log = LogManager.getLogger();

    private final String ID;

    public GetRentalHistoryRequest(String historyID) {
        this.ID = historyID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "GetRentalRequest{" +
                "historyID='" + ID + '\'' +
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
