package ilya.myasoedov.ocs.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ilya.myasoedov.ocs.di.qualifier.ScreenScope
import ilya.myasoedov.ocs.features.presentation.detail.DetailFragment
import ilya.myasoedov.ocs.features.presentation.detail.DetailFragmentModule
import ilya.myasoedov.ocs.features.presentation.search.SearchFragment
import ilya.myasoedov.ocs.features.presentation.search.SearchFragmentModule

@Module
abstract class FragmentInjectionsModule {

    @ScreenScope
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun searchFragmentInjector(): SearchFragment

    @ScreenScope
    @ContributesAndroidInjector(modules = [DetailFragmentModule::class])
    abstract fun detailsFragmentInjector(): DetailFragment
}