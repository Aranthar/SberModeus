package ru.sbermodeus.domain.repository

import ru.sbermodeus.domain.model.User
import java.util.UUID

interface UserRepository {
    suspend fun getUserById(id: UUID): User
    suspend fun updateSpecialization(userId: UUID, specializationId: UUID): User
}