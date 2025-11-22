package ru.sbermodeus.domain.model

import java.util.UUID

data class Specialization(
    val id: UUID,
    val name: String,
    val description: String,
    val avgSalary: Double,
    val avgCandidates: Double,
    val requiredSkills: List<SkillLevel>,
    val demandLevel: DemandLevel,
)
