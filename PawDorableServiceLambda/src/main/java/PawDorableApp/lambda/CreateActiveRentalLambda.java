package PawDorableApp.lambda;

import PawDorableApp.activity.request.CreateActiveRentalRequest;
import PawDorableApp.activity.results.CreateActiveRentalResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateActiveRentalLambda extends LambdaActivityRunner<CreateActiveRentalRequest, CreateActiveRentalResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateActiveRentalRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateActiveRentalRequest> input, Context context) {
        return super.runActivity(

                () -> {
                    CreateActiveRentalRequest createRequest = input.fromBody(CreateActiveRentalRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateActiveRentalRequest.builder()
                                    .withPetID(createRequest.getPetID())
                                    .withProfileID(claims.get("email"))
                                    .build()
                    );
                },
                (request,serviceComponent) ->{
                    try{
                        return serviceComponent.provideCreateActiveRentalActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
