package ru.sbermodeus.data.repository

import ru.sbermodeus.data.datastore.remote.UserDataStore
import ru.sbermodeus.data.mappers.toDomain
import ru.sbermodeus.domain.model.User
import ru.sbermodeus.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
) : UserRepository {
    override suspend fun getUserById(id: UUID): User? {
        return userDataStore
            .getUserById(id = id)
            ?.toDomain()
    }

    override suspend fun createUser(
        name: String,
        surname: String
    ): User? {
        return userDataStore
            .createUser(name = name, surname = surname)
            ?.toDomain()
    }

    override suspend fun updateSpecialization(
        userId: UUID,
        specializationId: UUID,
    ): User? {
        return userDataStore
            .updateSpecialization(
                userId = userId,
                specializationId = specializationId,
            )
            ?.toDomain()
    }

    override suspend fun addCourse(
        userId: UUID,
        specializationId: UUID
    ): User? {
        return userDataStore
            .addCourse(userId = userId, specializationId = specializationId)
            ?.toDomain()
    }

    override suspend fun completeCourse(
        userId: UUID,
        courseId: UUID
    ): User? {
        return userDataStore
            .completeCourse(userId = userId, courseId = courseId)
            ?.toDomain()
    }

    override suspend fun completeCourseForce(
        userId: UUID,
        courseId: UUID
    ): User? {
        return userDataStore
            .completeCourseForce(userId = userId, courseId = courseId)
            ?.toDomain()
    }
}