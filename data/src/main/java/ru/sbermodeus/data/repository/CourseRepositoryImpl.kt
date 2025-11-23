package ru.sbermodeus.data.repository

import ru.sbermodeus.data.datastore.remote.CourseDataStore
import ru.sbermodeus.data.mappers.toDomain
import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.Roadmap
import ru.sbermodeus.domain.repository.CourseRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepositoryImpl @Inject constructor(
    private val courseDataStore: CourseDataStore,
) : CourseRepository {
    override suspend fun getCourseById(courseId: UUID): Course? {
        return courseDataStore
            .getCourseById(courseId = courseId)
            ?.toDomain()
    }

    override suspend fun getAllCourses(): List<Course>? {
        return courseDataStore
            .getAllCourses()
            ?.map { it.toDomain() }
    }

    override suspend fun getRoadmap(userId: UUID): Roadmap? {
        return courseDataStore
            .getRoadmap(userId = userId)
            ?.toDomain()
    }
}