package PawDorableApp.lambda;


import PawDorableApp.activity.request.GetRentalHistoryRequest;
import PawDorableApp.activity.results.GetRentalHistoryResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetRentalHistoryLambda extends LambdaActivityRunner<GetRentalHistoryRequest, GetRentalHistoryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetRentalHistoryRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetRentalHistoryRequest> input, Context context) {
        return super.runActivity(
                () -> GetRentalHistoryRequest.builder()
                        .withHistoryID(input.fromBody(GetRentalHistoryRequest.class).getID())
                        .build(),
        (request,serviceComponent) ->
        {
            try {
                return serviceComponent.provideGetRentalHistoryActivity().handleRequest(request);
            }catch(Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        );
    }
}
