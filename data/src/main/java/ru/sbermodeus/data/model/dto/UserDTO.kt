package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserDTO(
    @SerialName(value = "id") val id: UUID,
    @SerialName(value = "login") val login: String,
)
