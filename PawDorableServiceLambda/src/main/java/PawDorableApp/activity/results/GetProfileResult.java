package PawDorableApp.activity.results;

import PawDorableApp.models.ProfileModel;

public class GetProfileResult {
    private final ProfileModel profile;

    private GetProfileResult(ProfileModel profile) {
        this.profile = profile;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "CreateProfileResult{" +
                "profile=" + profile +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private ProfileModel profile;

        public Builder withProfile(ProfileModel profile) {
            this.profile = profile;
            return this;
        }

        public GetProfileResult build() {
            return new GetProfileResult(profile);
        }
    }
}
