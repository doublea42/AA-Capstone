package PawDorableApp.dependency;

import PawDorableApp.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    CreateProfileActivity provideCreateProfileActivity();
    GetProfileActivity provideGetProfileActivity();
    UpdateProfileActivity provideUpdateProfileActivity();
    CreatePetActivity provideCreatePetActivity();
    CreateActiveRentalActivity provideCreateActiveRentalActivity();
}
