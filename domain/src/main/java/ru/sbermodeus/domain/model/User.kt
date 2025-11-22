package ru.sbermodeus.domain.model

import java.util.UUID

data class User(
    val id: UUID,
    val login: String,
    val name: String,
    val surname: String,
    val specialization: Specialization,
)
