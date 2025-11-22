package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sbermodeus.domain.model.DemandLevel

@Serializable
data class SpecializationDTO(
    @SerialName(value = "id") val id: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "avg_salary") val avgSalary: Double,
    @SerialName(value = "avg_candidates") val avgCandidates: Double,
    @SerialName(value = "required_skills") val requiredSkills: List<SkillLevelDTO>,
    @SerialName(value = "demand_level") val demandLevel: DemandLevel,
)
