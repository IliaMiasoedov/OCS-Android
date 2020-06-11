package ilya.myasoedov.ocs.features.presentation.search

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ilya.myasoedov.ocs.di.Provider
import ilya.myasoedov.ocs.di.qualifier.ScreenScope
import ilya.myasoedov.ocs.di.qualifier.ViewModelInjection

@Module
class SearchFragmentModule {

    @Provides
    @ScreenScope
    @ViewModelInjection
    fun provideDetailFragmentViewModel(
        fragment: SearchFragment,
        viewModelProvider: Provider<SearchFragmentViewModel>
    ): SearchFragmentViewModel =
        viewModelProvider.get(fragment, SearchFragmentViewModel::class)
}