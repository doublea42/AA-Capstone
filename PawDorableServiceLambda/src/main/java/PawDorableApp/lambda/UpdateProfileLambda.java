package PawDorableApp.lambda;

import PawDorableApp.activity.request.GetProfileRequest;
import PawDorableApp.activity.request.UpdateProfileRequest;
import PawDorableApp.activity.results.UpdateProfileResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateProfileLambda extends LambdaActivityRunner<UpdateProfileRequest, UpdateProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateProfileRequest>, LambdaResponse> {


    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateProfileRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateProfileRequest unauthenticatedRequest = input.fromBody(UpdateProfileRequest.class);
                    String profileEmailFromPath = input.getPathParameters().get("emailAddress");

                    return UpdateProfileRequest.builder()
                            .withEmailAddress(profileEmailFromPath)
                            .withFirstName(unauthenticatedRequest.getFirstName())
                            .withLastName(unauthenticatedRequest.getLastName())
                            .withAge(unauthenticatedRequest.getAge())
                            .build();
                },
                (request, serviceComponent) -> serviceComponent.provideUpdateProfileActivity().handleRequest(request)

        );


    }
}
