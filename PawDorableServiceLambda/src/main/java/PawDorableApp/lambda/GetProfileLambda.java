package PawDorableApp.lambda;

import PawDorableApp.activity.request.GetProfileRequest;
import PawDorableApp.activity.results.GetProfileResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetProfileLambda extends LambdaActivityRunner<GetProfileRequest, GetProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetProfileRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();
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
                (request,serviceComponent)-> {
                    try{
                        return serviceComponent.provideGetProfileActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
