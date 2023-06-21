package PawDorableApp.lambda;


import PawDorableApp.activity.request.GetPetRequest;
import PawDorableApp.activity.results.GetPetResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPetLambda extends LambdaActivityRunner<GetPetRequest, GetPetResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetPetRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPetRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath( path ->
                        GetPetRequest.builder()
                        .withID(path.get("id"))
                        .build()
                ),
                (request,serviceComponent) ->
                {
                    try {
                        return serviceComponent.provideGetPetActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
