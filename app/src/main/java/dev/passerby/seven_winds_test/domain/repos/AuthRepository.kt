package dev.passerby.seven_winds_test.domain.repos

import dev.passerby.seven_winds_test.domain.models.AuthDataModel

interface AuthRepository {
    suspend fun registerUser(): AuthDataModel
    suspend fun loginUser(): AuthDataModel
}