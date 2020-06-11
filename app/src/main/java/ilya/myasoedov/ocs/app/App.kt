package ilya.myasoedov.ocs.app

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ilya.myasoedov.ocs.di.component.DaggerAppComponent;
import ilya.myasoedov.ocs.providers.ImageLoadProvider
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var imageLoadProvider: ImageLoadProvider

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        imageLoadProvider.setup()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}