package dev.passerby.seven_winds_test.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.seven_winds_test.domain.models.CoffeeHousesListModel

interface CoffeeHousesRepository {

    fun getCoffeeHousesList(): LiveData<CoffeeHousesListModel>

    suspend fun loadCoffeeHousesList()
}