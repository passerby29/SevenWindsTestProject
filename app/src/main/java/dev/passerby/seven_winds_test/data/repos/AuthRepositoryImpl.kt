package dev.passerby.seven_winds_test.data.repos

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import dev.passerby.seven_winds_test.data.PREF_NAME
import dev.passerby.seven_winds_test.data.TOKEN_KEY
import dev.passerby.seven_winds_test.data.mappers.AuthMapper
import dev.passerby.seven_winds_test.data.models.dto.AuthDataDto
import dev.passerby.seven_winds_test.data.network.ApiFactory
import dev.passerby.seven_winds_test.data.network.BaseResponse
import dev.passerby.seven_winds_test.domain.models.AuthDataModel
import dev.passerby.seven_winds_test.domain.models.AuthUserDataModel
import dev.passerby.seven_winds_test.domain.repos.AuthRepository

class AuthRepositoryImpl(application: Application) : AuthRepository {

    private val sharedPreferences =
        application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private val apiService = ApiFactory.apiService
    private val authMapper = AuthMapper()
    private val authResult = MutableLiveData<BaseResponse<AuthDataDto>>()

    override suspend fun registerUser(userData: AuthUserDataModel): AuthDataModel {
        authResult.postValue(BaseResponse.Loading())

        try {
            val response = apiService.registerUser(authMapper.mapUserEntityToDto(userData))

            if (response.code() == 200) {
                authResult.postValue(BaseResponse.Success(response.body()))
                Log.d(TAG, "registerUserTry: ${response.body()}")
                editor.putString(TOKEN_KEY, response.body()?.token)
                return authMapper.mapResponseDtoToEntity(response.body()!!)
            } else {
                authResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "registerUserElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            authResult.postValue(BaseResponse.Error(ex.message))
            Log.d(TAG, "registerUserCatch: $ex")
        }
        return AuthDataModel("", 0)
    }

    override suspend fun loginUser(userData: AuthUserDataModel): AuthDataModel {
        authResult.postValue(BaseResponse.Loading())

        try {
            val response = apiService.loginUser(authMapper.mapUserEntityToDto(userData))

            if (response.code() == 200) {
                authResult.postValue(BaseResponse.Success(response.body()))
                Log.d(TAG, "loginUserTry: ${response.body()}")
                return authMapper.mapResponseDtoToEntity(response.body()!!)
            } else {
                authResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loginUserElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            authResult.postValue(BaseResponse.Error(ex.message))
            Log.d(TAG, "loginUserCatch: $ex")
        }
        return AuthDataModel("", 0)
    }

    companion object {
        private const val TAG = "AuthRepositoryImplTAG"
    }
}