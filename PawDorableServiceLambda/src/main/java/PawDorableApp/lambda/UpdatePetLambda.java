package PawDorableApp.lambda;

import PawDorableApp.activity.request.UpdatePetRequest;
import PawDorableApp.activity.results.UpdatePetResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdatePetLambda extends LambdaActivityRunner<UpdatePetRequest, UpdatePetResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdatePetRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdatePetRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdatePetRequest unauthenticatedRequest = input.fromBody(UpdatePetRequest.class);
                    return UpdatePetRequest.builder()
                            .withID(unauthenticatedRequest.getID())
                            .withAvailable(unauthenticatedRequest.getAvailable())
                            .build();
                },
                (request,serviceComponent) ->
                {
                    try {
                        return serviceComponent.provideUpdatePetActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
