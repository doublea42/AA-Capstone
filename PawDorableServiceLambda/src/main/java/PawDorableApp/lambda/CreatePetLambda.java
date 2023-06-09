package PawDorableApp.lambda;

import PawDorableApp.activity.request.CreatePetRequest;
import PawDorableApp.activity.results.CreatePetResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreatePetLambda extends LambdaActivityRunner<CreatePetRequest, CreatePetResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreatePetRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreatePetRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreatePetRequest unauthenticatedRequest = input.fromBody(CreatePetRequest.class);

                    return input.fromUserClaims(claims ->
                            CreatePetRequest.builder()
                                    .withOwnerEmail(claims.get("email"))
                                    .withName(unauthenticatedRequest.getName())
                                    .withKindOfPet(unauthenticatedRequest.getKindOfPet())
                                    .withAge(unauthenticatedRequest.getAge())
                                    .withGender(unauthenticatedRequest.getGender())
                                    .withAvailable(unauthenticatedRequest.getAvailable())
                                    .build());
                },
                (request,serviceComponent) ->{
                    try{
                        return serviceComponent.provideCreatePetActivity().handleRequest(request);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
        );
    }
}
