package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoadmapPeriodDTO(
    @SerialName(value = "period") val period: Int,
    @SerialName(value = "courses") val courses: List<CourseDTO>,
)
