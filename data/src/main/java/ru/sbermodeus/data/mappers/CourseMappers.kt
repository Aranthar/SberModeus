package ru.sbermodeus.data.mappers

import ru.sbermodeus.data.model.dto.CourseDTO
import ru.sbermodeus.data.model.dto.RoadmapDTO
import ru.sbermodeus.data.model.dto.RoadmapPeriodDTO
import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.Roadmap
import ru.sbermodeus.domain.model.RoadmapPeriod

fun CourseDTO.toDomain(): Course = Course(
    id = id,
    name = name,
    description = description,
    requiredSkillLevels = requiredSkillLevels.toDomain(),
    givenSkillLevels = givenSkillLevels.toDomain(),
)

fun RoadmapDTO.toDomain(): Roadmap = Roadmap(periods = periods.map { it.toDomain() })

fun RoadmapPeriodDTO.toDomain(): RoadmapPeriod = RoadmapPeriod(
    period = period,
    courses = courses.map { it.toDomain() },
)
