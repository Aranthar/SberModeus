package ru.sbermodeus.presentation.specialization

import ru.sbermodeus.data.model.dto.SpecializationDTO

data class SpecializationState(
    val specializationList: List<SpecializationDTO> = emptyList()
)