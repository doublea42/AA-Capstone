package PawDorableApp.lambda;

import PawDorableApp.activity.request.RemoveActiveRentalRequest;
import PawDorableApp.activity.results.RemoveActiveRentalResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RemoveActiveRentalLambda extends LambdaActivityRunner<RemoveActiveRentalRequest, RemoveActiveRentalResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveActiveRentalRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveActiveRentalRequest> input, Context context) {
        return super.runActivity(

                () -> input.fromPath( path ->
                        RemoveActiveRentalRequest.builder()
                                .withPetID(path.get("petID"))
                                .withProfileID(path.get("profileID"))
                                .withRentalScore(path.get("rentalScore"))
                                .build()
                ),
                (request,serviceComponent) ->
                {
                    try {
                        return serviceComponent.provideRemoveActiveRentalActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
