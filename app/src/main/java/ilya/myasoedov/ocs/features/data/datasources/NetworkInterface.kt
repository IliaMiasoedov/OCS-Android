package ilya.myasoedov.ocs.features.data.datasources

import ilya.myasoedov.ocs.features.data.models.Position
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {

    interface IPositions {
        @GET("positions.json")
        suspend fun getPositions(@Query("search") search: String, @Query("page") page: Long): List<Position>
    }
}