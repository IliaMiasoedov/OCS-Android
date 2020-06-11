package ilya.myasoedov.ocs.providers

import com.google.gson.GsonBuilder
import ilya.myasoedov.ocs.features.data.datasources.converter.GsonConverterFactory
import retrofit2.Converter

class ConverterFactoryProvider {

    private val converterFactory = GsonConverterFactory(GsonBuilder().setLenient().create())

    fun getConverterFactory(): Converter.Factory = converterFactory
}