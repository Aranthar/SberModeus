package ru.sbermodeus.data.datastore.remote

import java.util.UUID

object ApiConfig {
    const val API: String = "api"
    const val API_V1: String = "$API/v1"
    const val API_USERS: String = "$API_V1/users"
    const val API_SKILLS: String = "$API_V1/skills"
    const val API_USER_COURSE: String = "$API_USERS/course"
    const val API_COURSES: String = "$API_V1/courses"
    const val API_ROADMAP: String = "$API_V1/roadmap"

    //Courses
    fun getCourseById(id: UUID) = "$API_COURSES/$id"
    fun getAllCourses() = "$API_COURSES/all"
    fun getRoadmap() = "$API_COURSES/roadmap"

    // User
    fun getUserById(id: UUID) = "$API_USERS/$id"
    fun getUserSpecialization(userId: UUID, specializationId: UUID): String {
        return "$API_USERS/specialization?user_id=$userId&spec_id=$specializationId"
    }

    fun addUserCourse(userId: UUID, specializationId: UUID): String {
        return "$API_USER_COURSE?user_id=$userId&spec_id=$specializationId"
    }

    fun completeCourse(userId: UUID, courseId: UUID): String {
        return "$API_USER_COURSE?user_id=$userId&course_id=$courseId"
    }

    fun completeCourseForce(userId: UUID, courseId: UUID): String {
        return "$API_USER_COURSE/force?user_id=$userId&course_id=$courseId"
    }
}