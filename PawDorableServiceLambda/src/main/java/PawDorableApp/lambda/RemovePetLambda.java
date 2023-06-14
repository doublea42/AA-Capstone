package PawDorableApp.lambda;

import PawDorableApp.activity.request.GetPetRequest;
import PawDorableApp.activity.request.RemovePetRequest;
import PawDorableApp.activity.results.RemovePetResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RemovePetLambda extends LambdaActivityRunner<RemovePetRequest, RemovePetResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemovePetRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemovePetRequest> input, Context context) {
        return super.runActivity(
                () -> RemovePetRequest.builder()
                        .withID(input.fromBody(RemovePetRequest.class).getID())
                        .build()
                ,(request,serviceComponent) -> serviceComponent.provideRemovePetActivity().handleRequest(request)
        );
    }
}
