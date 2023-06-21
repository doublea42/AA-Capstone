package PawDorableApp.lambda;

import PawDorableApp.activity.request.GetActiveRentalRequest;
import PawDorableApp.activity.results.GetActiveRentalResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetActiveRentalLambda extends LambdaActivityRunner<GetActiveRentalRequest, GetActiveRentalResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetActiveRentalRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetActiveRentalRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath( path ->
                        GetActiveRentalRequest.builder()
                                .withRentalID(path.get("rentalID"))
                                .build()
                ),
                (request,serviceComponent) ->
                {
                    try {
                        return serviceComponent.provideGetActiveRentalActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
