package ru.sbermodeus.data.datastore.remote

import io.ktor.http.HttpMethod
import ru.sbermodeus.data.ktor.RequestManager
import ru.sbermodeus.data.model.dto.CourseDTO
import ru.sbermodeus.data.model.dto.RoadmapDTO
import ru.sbermodeus.data.utils.Utils.castOrNull
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseDataStore @Inject constructor(
    private val requestManager: RequestManager,
) {
    suspend fun getCourseById(courseId: UUID): CourseDTO? {
        val response = requestManager.createRequest(
            methodType = HttpMethod.Get,
            address = ApiConfig.getCourseById(id = courseId),
        )

        return response.castOrNull<CourseDTO>()
    }

    suspend fun getAllCourses(): List<CourseDTO>? {
        val response = requestManager.createRequest(
            methodType = HttpMethod.Get,
            address = ApiConfig.getAllCourses(),
        )

        return response.castOrNull<List<CourseDTO>>()
    }

    suspend fun getRoadmap(userId: UUID): RoadmapDTO? {
        val response = requestManager.createRequest(
            methodType = HttpMethod.Get,
            address = ApiConfig.getRoadmap(id = userId),
        )

        return response.castOrNull<RoadmapDTO>()
    }
}
