package dev.passerby.seven_winds_test.domain.usecases

import dev.passerby.seven_winds_test.domain.repos.AuthRepository

class LoginUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.loginUser()
}