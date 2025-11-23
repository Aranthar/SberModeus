package ru.sbermodeus.data.repository

import ru.sbermodeus.data.datastore.remote.CacheDataStore
import ru.sbermodeus.domain.repository.CacheRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepositoryImpl @Inject constructor(
    private val cacheDataStore: CacheDataStore,
) : CacheRepository {
    override suspend fun getMyId() = cacheDataStore.getMyId()
    override suspend fun setMyId(id: UUID) = cacheDataStore.setMyId(id = id)
}