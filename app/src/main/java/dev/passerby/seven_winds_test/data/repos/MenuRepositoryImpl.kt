package dev.passerby.seven_winds_test.data.repos

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.passerby.seven_winds_test.data.BEARER_KEY
import dev.passerby.seven_winds_test.data.PREF_NAME
import dev.passerby.seven_winds_test.data.TOKEN_KEY
import dev.passerby.seven_winds_test.data.mappers.MenuMapper
import dev.passerby.seven_winds_test.data.models.dto.MenuListDto
import dev.passerby.seven_winds_test.data.network.ApiFactory
import dev.passerby.seven_winds_test.data.network.BaseResponse
import dev.passerby.seven_winds_test.domain.models.MenuItemModel
import dev.passerby.seven_winds_test.domain.repos.MenuRepository

class MenuRepositoryImpl(application: Application) : MenuRepository {

    private val sharedPreferences = application.getSharedPreferences(
        PREF_NAME,
        Context.MODE_PRIVATE
    )
    private val apiService = ApiFactory.apiService
    private val menuMapper = MenuMapper()
    private val menuListResult = MutableLiveData<BaseResponse<MenuListDto>>()

    override suspend fun loadMenuList(id: Int): LiveData<List<MenuItemModel>> {
        val menuListLiveData = MutableLiveData<List<MenuItemModel>>()

        val token = sharedPreferences.getString(TOKEN_KEY, "").toString()

        val headersMap = HashMap<String, String>()
        headersMap[BEARER_KEY] = "Bearer $token"

        menuListResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadMenuList(id, headersMap)

            if (response.code() == 200) {
                menuListResult.value = BaseResponse.Success(response.body())
                menuListLiveData.value = response.body()?.map {
                    menuMapper.mapDtoItemToEntityItem(it)
                }
                Log.d(TAG, "loadMenuListTry: ${response.body()}")
                return menuListLiveData
            } else {
                menuListResult.value = BaseResponse.Error(response.message())
                Log.d(TAG, "loadMenuListElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            menuListResult.value = BaseResponse.Error(ex.message)
            Log.d(TAG, "loadMenuListCatch: ${ex.message}")
        }
        return menuListLiveData
    }

    companion object {
        private const val TAG = "MenuRepositoryImplTAG"
    }
}