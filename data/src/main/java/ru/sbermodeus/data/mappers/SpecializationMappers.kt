package ru.sbermodeus.data.mappers

import ru.sbermodeus.data.model.dto.SpecializationDTO
import ru.sbermodeus.domain.model.Specialization
import java.util.UUID

fun SpecializationDTO.toDomain(): Specialization = Specialization(
    id = UUID.fromString(id),
    name = name,
    description = description,
    avgSalary = avgSalary,
    avgCandidates = avgCandidates,
    requiredSkills = requiredSkills.toDomain(),
    demandLevel = demandLevel,
)

fun List<SpecializationDTO>.toDomain(): List<Specialization> = this.map { it.toDomain() }