package ilya.myasoedov.ocs.providers

import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitProvider @Inject constructor(
    baseUrlProvider: BaseUrlProvider,
    converterFactoryProvider: ConverterFactoryProvider,
    okHttpClientProvider: OkHttpClientProvider
) {
    private val retrofit = Retrofit.Builder().baseUrl(baseUrlProvider.getBaseUrl())
        .addConverterFactory(converterFactoryProvider.getConverterFactory())
        .client(okHttpClientProvider.getOkHttpClient()).build()

    fun getRetrofit(): Retrofit = retrofit
}