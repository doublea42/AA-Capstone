package PawDorableApp.dependency;

import PawDorableApp.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetPetActivity provideGetPetActivity();
    GetProfileActivity provideGetProfileActivity();
    GetRentalHistoryActivity provideGetRentalHistoryActivity();
    GetActiveRentalActivity provideGetActiveRentalActivity();
    CreatePetActivity provideCreatePetActivity();
    CreateProfileActivity provideCreateProfileActivity();
    CreateActiveRentalActivity provideCreateActiveRentalActivity();
    UpdatePetActivity provideUpdatePetActivity();
    UpdateProfileActivity provideUpdateProfileActivity();
    RemovePetActivity provideRemovePetActivity();
    RemoveActiveRentalActivity provideRemoveActiveRentalActivity();

}
