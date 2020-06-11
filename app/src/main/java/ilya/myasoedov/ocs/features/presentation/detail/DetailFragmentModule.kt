package ilya.myasoedov.ocs.features.presentation.detail

import dagger.Module
import dagger.Provides
import ilya.myasoedov.ocs.di.Provider
import ilya.myasoedov.ocs.di.qualifier.ScreenScope
import ilya.myasoedov.ocs.di.qualifier.ViewModelInjection

@Module
class DetailFragmentModule {

    @Provides
    @ScreenScope
    @ViewModelInjection
    fun provideDetailFragmentViewModel(
        fragment: DetailFragment,
        viewModelProvider: Provider<DetailFragmentViewModel>
    ): DetailFragmentViewModel =
        viewModelProvider.get(fragment, DetailFragmentViewModel::class)
}