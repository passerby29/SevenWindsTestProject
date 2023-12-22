package dev.passerby.seven_winds_test.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.seven_winds_test.domain.models.MenuItemModel

interface MenuRepository {

    suspend fun loadMenuList(id: Int): LiveData<List<MenuItemModel>>
}