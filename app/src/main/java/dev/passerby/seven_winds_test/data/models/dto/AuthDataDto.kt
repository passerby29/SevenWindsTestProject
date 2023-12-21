package dev.passerby.seven_winds_test.data.models.dto


import com.google.gson.annotations.SerializedName

data class AuthDataDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("tokenLifetime")
    val tokenLifetime: Int
)