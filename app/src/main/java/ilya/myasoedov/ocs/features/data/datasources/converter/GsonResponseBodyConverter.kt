package ilya.myasoedov.ocs.features.data.datasources.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

@Suppress("SpellCheckingInspection")
class GsonResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) :
    Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val jsonReader = gson.newJsonReader(value.charStream())
        value.use {
            return adapter.read(jsonReader)
        }
    }
}