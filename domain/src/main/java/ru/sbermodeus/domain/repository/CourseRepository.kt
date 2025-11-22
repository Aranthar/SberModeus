package ru.sbermodeus.domain.repository

import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.Roadmap
import java.util.UUID

interface CourseRepository {
    suspend fun getCourseById(courseId: UUID): Course
    suspend fun getAllCourses(): List<Course>
    suspend fun getRoadmap(userId: UUID): Roadmap
}