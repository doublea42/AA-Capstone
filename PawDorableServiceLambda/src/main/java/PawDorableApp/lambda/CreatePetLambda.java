package PawDorableApp.lambda;

import PawDorableApp.activity.request.CreatePetRequest;
import PawDorableApp.activity.results.CreatePetResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreatePetLambda extends LambdaActivityRunner<CreatePetRequest, CreatePetResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreatePetRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreatePetRequest> input, Context context) {
        return null;
    }
}
