package PawDorableApp.dependency;

import PawDorableApp.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    CreateActiveRentalActivity provideCreateActiveRentalActivity();
    CreatePetActivity provideCreatePetActivity();
    CreateProfileActivity provideCreateProfileActivity();
//    GetActiveRentalActivity provideGetActiveRentalActivity();
    GetPetActivity provideGetPetActivity();
    GetProfileActivity provideGetProfileActivity();
    GetRentalHistoryActivity provideGetRentalHistoryActivity();
//    RemoveActiveRentalActivity provideRemoveActiveRentalActivity();
//    RemovePetActivity provideRemovePetActivity();
//    UpdatePetActivity provideUpdatePetActivity();
    UpdateProfileActivity provideUpdateProfileActivity();

}
