package ru.sbermodeus.data.ktor

import android.net.http.HttpResponseCache
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import java.util.logging.Logger

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(engineFactory = OkHttp) {
            HttpResponseCache.install(plugin = ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            HttpResponseCache.install(plugin = WebSockets)
            HttpResponseCache.install(plugin = Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("MyLog", message)
                    }
                }

                R.attr.level = LogLevel.ALL
            }
        }
    }
}