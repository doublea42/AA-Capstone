package PawDorableApp.dynamodb;

import PawDorableApp.dynamodb.models.ActiveRental;
import PawDorableApp.metrics.MetricsPublisher;
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
        ActiveRental selectedActiveRental = this.dynamoDbMapper.load(ActiveRental.class, activeID);
        return  selectedActiveRental;
    }

    

}
