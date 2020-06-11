package ilya.myasoedov.ocs.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ilya.myasoedov.ocs.app.App
import ilya.myasoedov.ocs.features.data.datasources.OCSClient
import ilya.myasoedov.ocs.providers.NetworkInterfaceProvider
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideOCSClient(networkInterfaceProvider: NetworkInterfaceProvider): OCSClient =
        OCSClient(networkInterfaceProvider)
}