package dev.passerby.seven_winds_test.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffeeHouses")
data class CoffeeHouseItemDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val point: PointDbModel
)