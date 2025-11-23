package ru.sbermodeus.domain.repository

import ru.sbermodeus.domain.model.Specialization

interface SpecializationRepository {
    suspend fun getAllSpecializations(): List<Specialization>?
}