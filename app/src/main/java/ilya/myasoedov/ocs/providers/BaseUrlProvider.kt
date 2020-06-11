package ilya.myasoedov.ocs.providers

import javax.inject.Inject

private val SERVER_SCHEMA = ServerSchema.HTTPS

class BaseUrlProvider @Inject constructor() {

    private val baseUrl = SERVER_SCHEMA.serverSchema + "://" + "jobs.github.com/"

    fun getBaseUrl(): String = baseUrl
}

private enum class ServerSchema constructor(val serverSchema: String) {
    HTTP("http"),
    HTTPS("https")
}