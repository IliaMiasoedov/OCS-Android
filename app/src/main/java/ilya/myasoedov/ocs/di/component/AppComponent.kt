package ilya.myasoedov.ocs.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ilya.myasoedov.ocs.app.App
import ilya.myasoedov.ocs.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityInjectionsModule::class,
        FragmentInjectionsModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoriesModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}