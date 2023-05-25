package PawDorableApp.dependency;

import PawDorableApp.activity.CreateProfileActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    CreateProfileActivity provideCreateProfileActivity();
}
