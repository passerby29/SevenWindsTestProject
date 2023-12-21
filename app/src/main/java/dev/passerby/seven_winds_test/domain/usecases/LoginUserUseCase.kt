package dev.passerby.seven_winds_test.domain.usecases

import dev.passerby.seven_winds_test.domain.models.AuthUserDataModel
import dev.passerby.seven_winds_test.domain.repos.AuthRepository

class LoginUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(userData: AuthUserDataModel) = repository.loginUser(userData)
}