package ilya.myasoedov.ocs.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ilya.myasoedov.ocs.app.AppActivity
import ilya.myasoedov.ocs.app.AppActivityModule

@Module
abstract class ActivityInjectionsModule {

    @ContributesAndroidInjector(modules = [AppActivityModule::class])
    abstract fun appActivityInjector(): AppActivity
}