package dev.passerby.seven_winds_test.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.seven_winds_test.data.repos.AuthRepositoryImpl
import dev.passerby.seven_winds_test.domain.models.AuthUserDataModel
import dev.passerby.seven_winds_test.domain.usecases.LoginUserUseCase
import dev.passerby.seven_winds_test.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepositoryImpl()

    private val loginUserUseCase = LoginUserUseCase(repository)
    private val registerUserUseCase = RegisterUserUseCase(repository)

    private val _isLoginFieldValid = MutableLiveData<Boolean>()
    val isLoginFieldValid: LiveData<Boolean>
        get() = _isLoginFieldValid

    private val _isPasswordFieldValid = MutableLiveData<Boolean>()
    val isPasswordFieldValid: LiveData<Boolean>
        get() = _isPasswordFieldValid

    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean>
        get() = _isLoginSuccessful

    private val _isRegisterSuccessful = MutableLiveData<Boolean>()
    val isRegisterSuccessful: LiveData<Boolean>
        get() = _isRegisterSuccessful

    fun loginUser(authData: AuthUserDataModel) {
        if (!validateInput(authData.login, authData.password)) {
            return
        }
        viewModelScope.launch {
            val loginData = loginUserUseCase(authData)
            if (loginData.token.isNotEmpty()){
                _isLoginSuccessful.value = true
            } else {
                _isLoginFieldValid.value = false
                _isPasswordFieldValid.value = false
            }
        }
    }

    fun registerUser(authData: AuthUserDataModel) {
        if (!validateInput(authData.login, authData.password)) {
            return
        }
        viewModelScope.launch {
            val registerData = registerUserUseCase(authData)
            if (registerData.token.isNotEmpty()){
                _isLoginSuccessful.value = true
            } else {
                _isLoginFieldValid.value = false
            }
        }
    }

    private fun validateInput(login: String, password: String): Boolean {
        _isLoginFieldValid.value = login.isNotEmpty()
        _isPasswordFieldValid.value = password.isNotEmpty()
        return !(isLoginFieldValid.value == false || isPasswordFieldValid.value == false)
    }

    fun resetEmailField() {
        _isLoginFieldValid.value = true
    }

    fun resetPasswordField() {
        _isPasswordFieldValid.value = true
    }
}