package dev.passerby.seven_winds_test.domain.usecases

import dev.passerby.seven_winds_test.domain.repos.MenuRepository

class LoadMenuListUseCase(private val repository: MenuRepository) {
    suspend operator fun invoke(id: Int) = repository.loadMenuList(id)
}