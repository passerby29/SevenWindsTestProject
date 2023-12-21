package dev.passerby.seven_winds_test.data.network

import dev.passerby.seven_winds_test.data.models.dto.AuthDataDto
import dev.passerby.seven_winds_test.data.models.dto.AuthUserDataDto
import dev.passerby.seven_winds_test.data.models.dto.CoffeeHousesListDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun loginUser(@Body body: AuthUserDataDto): Response<AuthDataDto>

    @POST("auth/register")
    suspend fun registerUser(@Body body: AuthUserDataDto): Response<AuthDataDto>

    @POST("/locations")
    suspend fun loadCoffeeHousesList(@HeaderMap headers: Map<String, String>): Response<CoffeeHousesListDto>
}