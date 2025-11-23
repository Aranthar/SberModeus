package ru.sbermodeus.presentation.specialization

import ru.sbermodeus.domain.model.Specialization

data class SpecializationState(
    var specializationList: List<Specialization> = emptyList(),
    var selectedSpecialization: Specialization? = null,
    var confirmedSpecialization: Specialization? = null
)
