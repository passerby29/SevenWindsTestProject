package dev.passerby.seven_winds_test.data.models.dto


import com.google.gson.annotations.SerializedName

data class CoffeeHouseItemDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("point")
    val point: PointDto
)