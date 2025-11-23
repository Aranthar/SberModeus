package ru.sbermodeus.data.datastore.remote

import io.ktor.http.HttpMethod
import ru.sbermodeus.data.ktor.RequestManager
import ru.sbermodeus.data.model.dto.SpecializationDTO
import ru.sbermodeus.data.utils.Utils.castOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpecializationDataStore @Inject constructor(
    private val requestManager: RequestManager,
) {
    suspend fun getAllSpecializations(): List<SpecializationDTO>? {
        val response = requestManager.createRequest(
            methodType = HttpMethod.Get,
            address = ApiConfig.getAllSpecializations(),
        )

        return response.castOrNull<List<SpecializationDTO>>()
    }
}
