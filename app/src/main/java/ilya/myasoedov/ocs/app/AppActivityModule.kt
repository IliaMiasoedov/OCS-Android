package ilya.myasoedov.ocs.app

import dagger.Module
import dagger.Provides
import ilya.myasoedov.ocs.di.Provider
import ilya.myasoedov.ocs.di.qualifier.ViewModelInjection

@Module
class AppActivityModule {

    @Provides
    @ViewModelInjection
    fun provideAppActivityViewModel(
        activity: AppActivity,
        viewModelProvider: Provider<AppActivityViewModel>
    ): AppActivityViewModel = viewModelProvider.get(activity, AppActivityViewModel::class)
}