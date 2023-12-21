package dev.passerby.seven_winds_test.domain.usecases

import dev.passerby.seven_winds_test.domain.repos.CoffeeHousesRepository

class LoadCoffeeHousesListUseCase(private val repository: CoffeeHousesRepository) {
    suspend operator fun invoke() = repository.loadCoffeeHousesList()
}