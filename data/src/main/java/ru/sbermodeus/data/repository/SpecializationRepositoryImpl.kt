package ru.sbermodeus.data.repository

import ru.sbermodeus.data.datastore.remote.SpecializationDataStore
import ru.sbermodeus.data.mappers.toDomain
import ru.sbermodeus.domain.model.Specialization
import ru.sbermodeus.domain.repository.SpecializationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpecializationRepositoryImpl @Inject constructor(
    private val specializationDataStore: SpecializationDataStore,
) : SpecializationRepository {
    override suspend fun getAllSpecializations(): List<Specialization>? {
        return specializationDataStore
            .getAllSpecializations()
            ?.toDomain()
    }
}