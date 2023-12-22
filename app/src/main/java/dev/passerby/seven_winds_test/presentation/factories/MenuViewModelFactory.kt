package dev.passerby.seven_winds_test.presentation.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.passerby.seven_winds_test.presentation.viewmodels.MenuViewModel

class MenuViewModelFactory(
    private val application: Application,
    private val id: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(application, id) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}