package dev.passerby.seven_winds_test.data.models.db

import androidx.room.Entity

@Entity(tableName = "coffeeHouses")
data class CoffeeHouseItemDbModel(
    val id: Int,
    val name: String,
    val point: PointDbModel
)