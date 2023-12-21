package dev.passerby.seven_winds_test.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.passerby.seven_winds_test.data.models.db.CoffeeHouseItemDbModel
import dev.passerby.seven_winds_test.data.models.db.CoffeeHousesListDbModel

interface CoffeeHousesDao {

    @Query("select * from coffeeHouses")
    fun getCoffeeHousesList(): LiveData<CoffeeHousesListDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffeeHouse(coffeeHouses: List<CoffeeHouseItemDbModel>)
}