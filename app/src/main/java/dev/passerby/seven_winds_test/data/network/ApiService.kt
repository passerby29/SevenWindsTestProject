package dev.passerby.seven_winds_test.data.network

import dev.passerby.seven_winds_test.data.models.dto.AuthDataDto
import dev.passerby.seven_winds_test.data.models.dto.AuthUserDataDto
import dev.passerby.seven_winds_test.data.models.dto.CoffeeHousesListDto
import dev.passerby.seven_winds_test.data.models.dto.MenuListDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun loginUser(@Body body: AuthUserDataDto): Response<AuthDataDto>

    @POST("auth/register")
    suspend fun registerUser(@Body body: AuthUserDataDto): Response<AuthDataDto>

    @GET("/locations")
    suspend fun loadCoffeeHousesList(@HeaderMap headers: Map<String, String>): Response<CoffeeHousesListDto>

    @GET("/location/{id}/menu")
    suspend fun loadMenuList(
        @Path("id") id: Int,
        @HeaderMap headers: Map<String, String>
    ): Response<MenuListDto>
}