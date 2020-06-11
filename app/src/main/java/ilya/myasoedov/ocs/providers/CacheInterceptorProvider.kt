package ilya.myasoedov.ocs.providers

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CacheInterceptorProvider @Inject constructor(
    networkInfoProvider: NetworkInfoProvider
) {

    private val cacheInterceptor = CacheInterceptor(networkInfoProvider)

    fun getCacheInterceptor(): Interceptor = cacheInterceptor

    class CacheInterceptor(private val networkInfoProvider: NetworkInfoProvider) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request()
                .newBuilder()
            if (!networkInfoProvider.isNetworkAvailable()) {
                builder.cacheControl(CacheControl.FORCE_CACHE)
            }
            return chain.proceed(builder.build())
        }
    }
}