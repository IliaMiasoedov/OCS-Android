package ilya.myasoedov.ocs.features.domain.usecases

import ilya.myasoedov.ocs.features.domain.entites.Position
import ilya.myasoedov.ocs.features.domain.repositories.IPositionsRepository
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor(
    private val positionsRepository: IPositionsRepository
) {

    suspend fun getPositions(
        search: String,
        page: Long
    ): Result<List<Position>> {
        return positionsRepository.getPositions(search = search, page = page)
    }
}