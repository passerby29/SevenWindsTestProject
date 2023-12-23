package dev.passerby.seven_winds_test.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.seven_winds_test.data.repos.MenuRepositoryImpl
import dev.passerby.seven_winds_test.domain.models.MenuItemModel
import dev.passerby.seven_winds_test.domain.usecases.LoadMenuListUseCase
import kotlinx.coroutines.launch

class MenuViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val repository = MenuRepositoryImpl(application)
    private val loadMenuListUseCase = LoadMenuListUseCase(repository)

    private val _menuList = MutableLiveData<List<MenuItemModel>>()
    val menuList: LiveData<List<MenuItemModel>>
        get() = _menuList

    fun loadMenuList(id: Int){
        viewModelScope.launch {
            _menuList.value = loadMenuListUseCase(id).value
        }
    }

    fun plusCount(position: Int){
        menuList.value?.get(position)?.plusCount()
    }

    fun minusCount(position: Int){
        menuList.value?.get(position)?.minusCount()
    }
}