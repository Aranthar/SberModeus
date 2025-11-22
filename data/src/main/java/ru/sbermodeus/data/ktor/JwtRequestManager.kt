package ru.sbermodeus.data.ktor

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JwtRequestManager @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun createRequest(
        methodType: HttpMethod,
        address: String,
        body: Any? = null,
        parameters: Map<String, Any?> = mapOf(),
    ): HttpResponse? {
        val response = executeRequest(address, methodType, body, parameters) { url, block ->
            httpClient.request(url, block)
        }

        executeRequest(address, methodType, body, parameters) { url, block ->
            httpClient.request(url, block)
        }

        return response
    }

    private suspend fun <T> executeRequest(
        address: String,
        methodType: HttpMethod,
        body: Any?,
        parameters: Map<String, Any?>? = null,
        requestBuilder: suspend (String, HttpRequestBuilder.() -> Unit) -> T,
    ): T? {
        return withContext(Dispatchers.IO) {
            try {
                requestBuilder(address) {
                    method = methodType

                    if (body !is MultiPartFormDataContent) {
                        header(key = HttpHeaders.ContentType, value = ContentType.Application.Json)
                        contentType(type = ContentType.Application.Json)
                    }

                    body?.let { setBody(it) }
                    parameters?.forEach { (key, value) -> parameter(key, value) }
                }
            } catch (e: Exception) {
                Log.e("MyLog", e.stackTraceToString())
                null
            }
        }
    }
}