package ilya.myasoedov.ocs.features.data.repositories

import ilya.myasoedov.ocs.features.data.datasources.OCSClient
import ilya.myasoedov.ocs.features.domain.entites.Position
import ilya.myasoedov.ocs.features.domain.repositories.IPositionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PositionsRepository @Inject constructor(
    private val ocsClient: OCSClient
) : IPositionsRepository {

    @Suppress("RemoveExplicitTypeArguments")
    override suspend fun getPositions(
        search: String,
        page: Long
    ): Result<List<Position>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = ocsClient.getPositions(search, page)
                Result.success(result.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure<List<Position>>(e)
            }
        }
    }
}