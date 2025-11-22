package ru.sbermodeus.presentation.specialization

import ru.sbermodeus.domain.model.Specialization

data class SpecializationState(
    val specializationList: List<Specialization> = emptyList(),
)