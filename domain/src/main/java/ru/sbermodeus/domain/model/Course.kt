package ru.sbermodeus.domain.model

import java.util.UUID

data class Course(
    val id: UUID,
    val name: String,
    val description: String,
    val requiredSkillLevels: List<SkillLevel>,
    val givenSkillLevels: List<SkillLevel>,
)
