package dev.passerby.seven_winds_test.data.repos

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import dev.passerby.seven_winds_test.data.PREF_NAME
import dev.passerby.seven_winds_test.data.TOKEN_KEY
import dev.passerby.seven_winds_test.data.database.AppDatabase
import dev.passerby.seven_winds_test.data.mappers.CoffeeHousesMapper
import dev.passerby.seven_winds_test.data.models.dto.CoffeeHousesListDto
import dev.passerby.seven_winds_test.data.network.ApiFactory
import dev.passerby.seven_winds_test.data.network.BaseResponse
import dev.passerby.seven_winds_test.domain.models.CoffeeHousesListModel
import dev.passerby.seven_winds_test.domain.repos.CoffeeHousesRepository

class CoffeeHousesRepositoryImpl(application: Application) : CoffeeHousesRepository {

    private val db = AppDatabase.getInstance(application)
    private val coffeeHousesDao = db.coffeeHousesDao()

    private val sharedPreferences = application.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    private val apiService = ApiFactory.apiService
    private val coffeeHousesMapper = CoffeeHousesMapper()
    private val coffeeHousesListResult = MutableLiveData<BaseResponse<CoffeeHousesListDto>>()

    override fun getCoffeeHousesList(): LiveData<CoffeeHousesListModel> {
        val coffeeHousesList = coffeeHousesDao.getCoffeeHousesList()
        return coffeeHousesList.map { coffeeHousesMapper.mapDbModelToEntity(it) }
    }

    override suspend fun loadCoffeeHousesList() {
        val headersMap = HashMap<String, String>()
        headersMap[TOKEN_KEY] = sharedPreferences.getString(TOKEN_KEY, "").toString()

        coffeeHousesListResult.postValue(BaseResponse.Loading())

        try {
            val response = apiService.loadCoffeeHousesList(headersMap)

            if (response.code() == 200) {
                coffeeHousesListResult.postValue(BaseResponse.Success(response.body()!!))
                val coffeeHouses = response.body()?.map {
                    coffeeHousesMapper.mapDtoItemToDbModelItem(it)
                }
                coffeeHousesDao.insertCoffeeHouse(coffeeHouses ?: emptyList())
                Log.d(TAG, "loadCoffeeHousesListTry: ${response.isSuccessful}")
            } else {
                coffeeHousesListResult.value = BaseResponse.Error(response.message())
                Log.d(TAG, "loadCoffeeHousesListElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            coffeeHousesListResult.value = BaseResponse.Error(ex.message)
            Log.d(TAG, "loadCoffeeHousesListCatch: ${ex.message}")
        }
    }

    companion object {
        private const val TAG = "CoffeeHousesRepositoryImplTAG"
    }
}