package ilya.myasoedov.ocs.features.data.datasources

import ilya.myasoedov.ocs.features.data.models.Position
import ilya.myasoedov.ocs.providers.NetworkInterfaceProvider
import javax.inject.Inject

class OCSClient @Inject constructor(networkInterfaceProvider: NetworkInterfaceProvider) {

    private val positionsInterface = networkInterfaceProvider.getPositionsInterface()

    suspend fun getPositions(
        search: String,
        page: Long
    ): List<Position> = positionsInterface.getPositions(search = search, page = page)
}