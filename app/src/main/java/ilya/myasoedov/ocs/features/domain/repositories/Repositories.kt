package ilya.myasoedov.ocs.features.domain.repositories

import ilya.myasoedov.ocs.features.domain.entites.Position

interface IPositionsRepository {

    suspend fun getPositions(search: String, page: Long): Result<List<Position>>
}