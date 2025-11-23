package ru.sbermodeus.data.datastore.remote

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "cache_prefs")
private val USER_ID_KEY = stringPreferencesKey(name = "user_id")

@Singleton
class CacheDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend fun getMyId(): UUID? {
        val id = context.dataStore.data
            .map { prefs -> prefs[USER_ID_KEY] }
            .first()
        return id?.let { UUID.fromString(it) }
    }

    suspend fun setMyId(id: UUID) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = id.toString()
        }
    }
}
