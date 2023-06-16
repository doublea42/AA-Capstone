package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.exceptions.RentalHistoryNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class RentalHistoryDao {
    private final Logger log = LogManager.getLogger();
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public RentalHistoryDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public RentalHistory getRentalHistory(String historyID){

        if(historyID == null){
            metricsPublisher.addCount(MetricsConstants.GET_RENTAL_HISTORY_NOT_FOUND_COUNT,1);
            throw new RentalHistoryNotFoundException("could not find rental history with id: " + historyID );
        }
        RentalHistory selectedRentalHistory = this.dynamoDbMapper.load(RentalHistory.class, historyID);
        metricsPublisher.addCount(MetricsConstants.GET_RENTAL_HISTORY_NOT_FOUND_COUNT,0);
        return selectedRentalHistory;
    }

    public RentalHistory saveRentalHistory(String petID, String profileID){

        RentalHistory selectedRentalHistory = this.findRentalHistory(petID, profileID);

        if(selectedRentalHistory == null){
            selectedRentalHistory = this.saveNewRentalHistory(petID,profileID);
        }

        return selectedRentalHistory;
    }

    private RentalHistory saveNewRentalHistory(String petID, String profileID){

        RentalHistory selectedRentalHistory = new RentalHistory();

        selectedRentalHistory.setID("RH_" + PawDorableServiceUtils.generateId());
        selectedRentalHistory.setPetID(petID);
        selectedRentalHistory.setProfileID(profileID);
        selectedRentalHistory.setTimesRented(0);
        selectedRentalHistory.setScore(1.0);

        dynamoDbMapper.save(selectedRentalHistory);
        return selectedRentalHistory;
    }

    public void UpdateRentalHistory(String ID, String score){

        RentalHistory selectedRentalHistory = this.getRentalHistory(ID);
        int newScore = Integer.parseInt(score);
        int timesRented = selectedRentalHistory.getTimesRented();

        Double updateScore = updateScore(timesRented, selectedRentalHistory.getScore(),newScore);
        selectedRentalHistory.setTimesRented(timesRented + 1);
        selectedRentalHistory.setScore(updateScore);

        dynamoDbMapper.save(selectedRentalHistory);
    }

    public RentalHistory findRentalHistory(String petID, String profileID){

        RentalHistory selectedRentalHistory = null;
        DynamoDBQueryExpression<RentalHistory> queryExpression = new DynamoDBQueryExpression<>();
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":profileID", new AttributeValue().withS(profileID));
        valueMap.put(":petID", new AttributeValue().withS(petID));
        queryExpression.withIndexName("RentalHistoryByProfileIDIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("profileID = :profileID and petID = :petID")
                .withExpressionAttributeValues(valueMap);



        List<RentalHistory> searchList = dynamoDbMapper.query(RentalHistory.class, queryExpression);

        selectedRentalHistory = searchList.get(0);

        return selectedRentalHistory;
    }



    private Double updateScore(int times, Double score, int newScore){
        return((score * times) + newScore) / (times + 1);
    }

}
