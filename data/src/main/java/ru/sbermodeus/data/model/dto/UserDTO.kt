package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName(value = "id") val id: String,
    @SerialName(value = "login") val login: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "surname") val surname: String,
    @SerialName(value = "specialization") val specialization: SpecializationDTO? = null,
    @SerialName(value = "active_courses") val activeCourses: List<CourseDTO>? = null,
    @SerialName(value = "completed_courses") val completedCourses: List<CourseDTO>? = null,
    @SerialName(value = "skills") val skills: List<SkillLevelDTO>? = null,
)
