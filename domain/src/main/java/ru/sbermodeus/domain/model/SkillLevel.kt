package ru.sbermodeus.domain.model

import java.util.UUID

data class SkillLevel(
    val id: UUID,
    val name: String,
    val level: Int,
)
