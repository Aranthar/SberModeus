package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class CourseDTO(
    @SerialName(value = "id") val id: UUID,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "required_skill_Levels") val requiredSkillLevels: List<SkillLevelDTO>,
    @SerialName(value = "given_skill_levels") val givenSkillLevels: List<SkillLevelDTO>,
)
