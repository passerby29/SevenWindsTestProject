package dev.passerby.seven_winds_test.domain.models

data class AuthDataModel(
    val token: String,
    val tokenLifetime: Int
)