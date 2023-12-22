package dev.passerby.seven_winds_test.data.models.dto


import com.google.gson.annotations.SerializedName

data class MenuItemDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)