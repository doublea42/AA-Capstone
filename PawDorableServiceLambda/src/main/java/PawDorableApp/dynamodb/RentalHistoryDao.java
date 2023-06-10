package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.RentalHistory;
import PawDorableApp.exceptions.RentalHistoryNotFoundException;
import PawDorableApp.metrics.MetricsConstants;
import PawDorableApp.metrics.MetricsPublisher;
import PawDorableApp.utils.PawDorableServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    public RentalHistory saveRentalHistory(boolean isNew, String id, String petID, String profileID, Double score){

        RentalHistory selectedRentalHistory = new RentalHistory();

        if(isNew){
            selectedRentalHistory.setHistoryID(PawDorableServiceUtils.generateId());
            selectedRentalHistory.setTimesRented(1);
            selectedRentalHistory.setScore(score);
        }

        RentalHistory tempRentalHistory = this.getRentalHistory(id);
        int oldTimesRented = tempRentalHistory.getTimesRented();
        Double newScore = updateScore(oldTimesRented, tempRentalHistory.getScore(), score);

        selectedRentalHistory.setHistoryID(id);
        selectedRentalHistory.setPetID(petID);
        selectedRentalHistory.setProfileID(profileID);
        selectedRentalHistory.setTimesRented(oldTimesRented + 1);
        selectedRentalHistory.setScore(newScore);

        dynamoDbMapper.save(selectedRentalHistory);

        return selectedRentalHistory;
    }

    private Double updateScore(int times, Double score, Double newScore){
        return((score * times) + newScore) / (times + 1);
    }

}
