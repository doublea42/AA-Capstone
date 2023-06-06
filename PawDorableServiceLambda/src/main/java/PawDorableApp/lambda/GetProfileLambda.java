package PawDorableApp.lambda;

import PawDorableApp.activity.request.GetProfileRequest;
import PawDorableApp.activity.results.GetProfileResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetProfileLambda extends LambdaActivityRunner<GetProfileRequest, GetProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetProfileRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetProfileRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path -> GetProfileRequest.builder()
                        .withEmailAddress(path.get("emailAddress")).build()),
                (request,serviceComponent)->
                        serviceComponent.provideGetProfileActivity().handleRequest(request)
        );
    }
}
