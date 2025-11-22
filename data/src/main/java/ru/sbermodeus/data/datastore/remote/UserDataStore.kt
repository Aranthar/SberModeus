package ru.sbermodeus.data.datastore.remote

import io.ktor.http.HttpMethod
import ru.sbermodeus.data.ktor.RequestManager
import ru.sbermodeus.data.model.dto.UserDTO
import ru.sbermodeus.data.utils.Utils.castOrNull
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStore @Inject constructor(
    private val requestManager: RequestManager,
) {
    suspend fun getUserById(id: UUID): UserDTO? {
        val response = requestManager.createRequest(
            methodType = HttpMethod.Get,
            address = ApiConfig.getUserById(id = id),
        )

        return response.castOrNull<UserDTO>()
    }

    suspend fun updateSpecialization(userId: UUID, specializationId: UUID): UserDTO? {
        val response = requestManager.createRequest(
            methodType = HttpMethod.Get,
            address = ApiConfig.getUserSpecialization(
                userId = userId,
                specializationId = specializationId,
            ),
        )

        return response.castOrNull<UserDTO>()
    }
}