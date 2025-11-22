package ru.sbermodeus.data.mappers

import ru.sbermodeus.data.model.dto.SkillLevelDTO
import ru.sbermodeus.data.model.dto.SpecializationDTO
import ru.sbermodeus.data.model.dto.UserDTO
import ru.sbermodeus.domain.model.SkillLevel
import ru.sbermodeus.domain.model.Specialization
import ru.sbermodeus.domain.model.User
import java.util.UUID

fun UserDTO.toDomain(): User = User(
    id = UUID.fromString(id),
    login = login,
    name = name,
    surname = surname,
    specialization = specialization.toDomain(),
)

fun SpecializationDTO.toDomain(): Specialization = Specialization(
    id = UUID.fromString(id),
    name = name,
    description = description,
    avgSalary = avgSalary,
    avgCandidates = avgCandidates,
    requiredSkills = requiredSkills.toDomain(),
    demandLevel = demandLevel,
)

fun List<SkillLevelDTO>.toDomain(): List<SkillLevel> = this.map { it.toDomain() }

fun SkillLevelDTO.toDomain(): SkillLevel = SkillLevel(
    id = UUID.fromString(id),
    name = name,
    level = level
)