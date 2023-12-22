package dev.passerby.seven_winds_test.domain.usecases

import dev.passerby.seven_winds_test.domain.repos.CoffeeHousesRepository

class GetCoffeeHousesListUseCase(private val repository: CoffeeHousesRepository) {
    operator fun invoke() = repository.getCoffeeHousesList()
}