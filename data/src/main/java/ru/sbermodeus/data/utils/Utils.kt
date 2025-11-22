package ru.sbermodeus.data.utils

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

object Utils {
    suspend inline fun <reified T> HttpResponse?.castOrNull(): T? {
        if (this == null) return null

        return try {
            this.body<T>()
        } catch (e: Throwable) {
            Log.e("MyLog", e.toString())
            null
        }
    }
}