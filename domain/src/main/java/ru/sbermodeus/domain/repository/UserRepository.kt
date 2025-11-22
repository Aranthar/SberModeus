package ru.sbermodeus.domain.repository

import ru.sbermodeus.domain.model.User
import java.util.UUID

interface UserRepository {
    suspend fun getUserById(id: UUID): User?
    suspend fun createUser(name: String, surname: String): User?
    suspend fun updateSpecialization(userId: UUID, specializationId: UUID): User?
    suspend fun addCourse(userId: UUID, specializationId: UUID): User?
    suspend fun completeCourse(userId: UUID, courseId: UUID): User?
    suspend fun completeCourseForce(userId: UUID, courseId: UUID): User?
}