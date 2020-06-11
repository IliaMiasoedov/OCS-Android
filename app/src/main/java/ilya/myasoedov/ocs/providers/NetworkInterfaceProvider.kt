package ilya.myasoedov.ocs.providers

import ilya.myasoedov.ocs.features.data.datasources.NetworkInterface
import javax.inject.Inject

class NetworkInterfaceProvider @Inject constructor(retrofitProvider: RetrofitProvider) {

    private val positionsImpl =
        retrofitProvider.getRetrofit().create(NetworkInterface.IPositions::class.java)

    fun getPositionsInterface(): NetworkInterface.IPositions = positionsImpl
}