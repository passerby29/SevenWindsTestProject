package dev.passerby.seven_winds_test.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.passerby.seven_winds_test.data.models.db.CoffeeHouseItemDbModel

@Dao
interface CoffeeHousesDao {
    @Query("select * from coffeeHouses")
    fun getCoffeeHousesList(): LiveData<List<CoffeeHouseItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffeeHouse(coffeeHouses: List<CoffeeHouseItemDbModel>)
}