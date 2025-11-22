package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class SpecializationDTO(
    @SerialName(value = "id") val id: UUID,
    @SerialName(value = "name") val name: String,
)
