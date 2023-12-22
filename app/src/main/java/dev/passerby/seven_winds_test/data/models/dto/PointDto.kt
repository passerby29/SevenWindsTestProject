package dev.passerby.seven_winds_test.data.models.dto


import com.google.gson.annotations.SerializedName

data class PointDto(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)