package PawDorableApp.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonDeserialize(builder = GetRentalRequest.Builder.class)
public class GetRentalRequest {
//    private final Logger log = LogManager.getLogger();

    private final String historyID;

    public GetRentalRequest(String historyID) {
        this.historyID = historyID;
    }

    public String getHistoryID() {
        return historyID;
    }

    @Override
    public String toString() {
        return "GetRentalRequest{" +
                "historyID='" + historyID + '\'' +
                '}';
    }

    public static Builder builder(){return new Builder();}
    @JsonPOJOBuilder
    public static class Builder{
        private String historyID;

        public Builder withHistoryID(String id){
            this.historyID = id;
            return this;
        }
        public GetRentalRequest build(){return new GetRentalRequest(historyID);}
    }
}
