package dev.passerby.seven_winds_test.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.passerby.seven_winds_test.data.repos.CoffeeHousesRepositoryImpl
import dev.passerby.seven_winds_test.domain.usecases.GetCoffeeHousesListUseCase
import dev.passerby.seven_winds_test.domain.usecases.LoadCoffeeHousesListUseCase
import kotlinx.coroutines.launch

class CoffeeHousesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoffeeHousesRepositoryImpl(application)
    private val getCoffeeHousesListUseCase = GetCoffeeHousesListUseCase(repository)
    private val loadCoffeeHousesListUseCase = LoadCoffeeHousesListUseCase(repository)

    val coffeeHouses = getCoffeeHousesListUseCase()

    init {
        viewModelScope.launch {
            loadCoffeeHousesListUseCase()
        }
    }
}