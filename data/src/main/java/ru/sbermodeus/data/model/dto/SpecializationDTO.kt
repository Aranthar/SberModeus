package ru.sbermodeus.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sbermodeus.domain.model.DemandLevel
import java.util.UUID

@Serializable
data class SpecializationDTO(
    @SerialName(value = "id") val id: UUID,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "avg_salary") val avgSalary: Double,
    @SerialName(value = "avg_candidates") val avgCandidates: Double,
    @SerialName(value = "skills") val skills: List<SkillLevelDTO>,
    @SerialName(value = "demand_level") val demandLevel: DemandLevel,
)
