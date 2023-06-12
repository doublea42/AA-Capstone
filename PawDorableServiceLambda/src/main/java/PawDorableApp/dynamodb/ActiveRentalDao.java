package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.ActiveRental;
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
public class ActiveRentalDao {
    private final Logger log = LogManager.getLogger();
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public ActiveRentalDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public ActiveRental getActiveRental(String activeID){
        if(activeID == null){
            metricsPublisher.addCount(MetricsConstants.GET_ACTIVE_RENTAL_HISTORY_NOT_FOUND_COUNT,1);
            throw new RentalHistoryNotFoundException("could not find an active rental history with id: " + activeID );
        }
        ActiveRental selectedActiveRental = this.dynamoDbMapper.load(ActiveRental.class, activeID);
        metricsPublisher.addCount(MetricsConstants.GET_ACTIVE_RENTAL_HISTORY_NOT_FOUND_COUNT,0);
        return  selectedActiveRental;
    }

    public ActiveRental saveNewActiveRental(String rentalID){

        ActiveRental selectedActiveRental = new ActiveRental();
        selectedActiveRental.setRentalID(PawDorableServiceUtils.generateId());
        selectedActiveRental.setRentalHistory(rentalID);

        return selectedActiveRental;
    }

    public Boolean removeActiveRental(String activeRentalID){
        ActiveRental tempActive = this.getActiveRental(activeRentalID);
        dynamoDbMapper.delete(tempActive);
        return this.getActiveRental(activeRentalID) == null;
    }

}
