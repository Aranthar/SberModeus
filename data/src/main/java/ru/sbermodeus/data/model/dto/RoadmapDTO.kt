package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoadmapDTO(
    @SerialName(value = "periods") val periods: List<RoadmapPeriodDTO>
)
