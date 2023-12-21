package dev.passerby.seven_winds_test.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.seven_winds_test.domain.models.CoffeeHouseItemModel

interface CoffeeHousesRepository {

    fun getCoffeeHousesList(): LiveData<List<CoffeeHouseItemModel>>

    suspend fun loadCoffeeHousesList()
}