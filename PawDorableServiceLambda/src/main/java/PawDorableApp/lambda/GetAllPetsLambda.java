package PawDorableApp.lambda;

import PawDorableApp.activity.request.GetAllPetsAvailableRequest;
import PawDorableApp.activity.request.GetProfileRequest;
import PawDorableApp.activity.results.GetAllPetsAvailableResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllPetsLambda extends LambdaActivityRunner<GetAllPetsAvailableRequest, GetAllPetsAvailableResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllPetsAvailableRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllPetsAvailableRequest> input, Context context) {
        return super.runActivity(
                        () -> input.fromUserClaims(claims -> GetAllPetsAvailableRequest.builder()
                                .withProfileID(claims.get("emailAddress")).build()),
                (request,serviceComponent) ->
                {
                    try {
                        return serviceComponent.provideGetAllPetsAvailableActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
