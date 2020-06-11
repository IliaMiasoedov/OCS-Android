package ilya.myasoedov.ocs.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ilya.myasoedov.ocs.providers.BaseUrlProvider
import ilya.myasoedov.ocs.providers.ConverterFactoryProvider
import ilya.myasoedov.ocs.providers.ImageLoadProvider
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrlProvider(): BaseUrlProvider = BaseUrlProvider()

    @Provides
    @Singleton
    fun provideConverterFactoryProvider(): ConverterFactoryProvider = ConverterFactoryProvider()

    @Provides
    @Singleton
    fun provideImageLoadProvider(context: Context): ImageLoadProvider = ImageLoadProvider(context)
}