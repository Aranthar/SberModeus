package ru.sbermodeus.domain.repository

import java.util.UUID

interface CacheRepository {
    suspend fun getMyId(): UUID?
    suspend fun setMyId(id: UUID)
}