package PawDorableApp.lambda;

import PawDorableApp.activity.request.CreateProfileRequest;
import PawDorableApp.activity.results.CreateProfileResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateProfileLambda extends LambdaActivityRunner<CreateProfileRequest, CreateProfileResult>
implements RequestHandler<AuthenticatedLambdaRequest<CreateProfileRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateProfileRequest> input, Context context) {
        return super.runActivity(
                () -> {
//                    log.info("--------------> lambda 1{} <------------------", input);
                    CreateProfileRequest unauthenticatedRequest = input.fromBody(CreateProfileRequest.class);
//                    log.info("--------------> lambda 2 request body <------------------", unauthenticatedRequest.toString());
                    return input.fromUserClaims(claims ->
                            CreateProfileRequest.builder()
                                    .withEmailAddress(unauthenticatedRequest.getEmailAddress())
//                                    .withEmail(claims.get("email"))
                                    .withFirstName(unauthenticatedRequest.getFirstName())
                                    .withLastName(unauthenticatedRequest.getLastName())
                                    .withAge(unauthenticatedRequest.getAge())
                                    .build());
                },
                (request,serviceComponent) ->
                        serviceComponent.provideCreateProfileActivity().handleRequest(request)
        );
    }
}
