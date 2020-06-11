package ilya.myasoedov.ocs.di.module

import dagger.Module
import dagger.Provides
import ilya.myasoedov.ocs.features.data.datasources.OCSClient
import ilya.myasoedov.ocs.features.data.repositories.PositionsRepository
import ilya.myasoedov.ocs.features.domain.repositories.IPositionsRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun providePositionsRepository(ocsClient: OCSClient): IPositionsRepository =
        PositionsRepository(ocsClient)
}