package dev.passerby.seven_winds_test.domain.repos

import dev.passerby.seven_winds_test.domain.models.AuthDataModel
import dev.passerby.seven_winds_test.domain.models.AuthUserDataModel

interface AuthRepository {
    suspend fun registerUser(userData: AuthUserDataModel): AuthDataModel
    suspend fun loginUser(userData: AuthUserDataModel): AuthDataModel
}