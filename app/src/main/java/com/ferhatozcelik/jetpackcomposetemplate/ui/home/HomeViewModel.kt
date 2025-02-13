package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.dao.ExampleDao
import com.ferhatozcelik.jetpackcomposetemplate.data.entity.UserPreferences
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CurrencyResponse
import com.ferhatozcelik.jetpackcomposetemplate.data.model.UiState
import com.ferhatozcelik.jetpackcomposetemplate.data.remote.AppApi
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

// Mock Api for Preview
class PreviewAppApi : AppApi {
    override suspend fun getCryptoData(): Response<List<CryptoData>> {
        delay(1000)
        // Return mock data
        return Response.success(generateMockCryptoDataList(20))
    }

    override suspend fun getSekConversionRate(): Response<CurrencyResponse> {
        TODO("Not yet implemented")
    }
}

// Mock Dao for Preview
class PreviewExampleDao : ExampleDao {

    override fun getExampleData(): List<UserPreferences> {
        return emptyList()
    }

    override fun getCurrencyPreference(): List<UserPreferences> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(search: UserPreferences?) {

    }

    override suspend fun update(search: UserPreferences) {

    }

    override suspend fun delete(search: UserPreferences) {

    }
}

// Mock Repository for Preview
@Singleton
open class PreviewCryptoCoinRepository @Inject constructor(
    cryptoCoinApi: PreviewAppApi,
    exampleDao: PreviewExampleDao
) : CryptoCoinRepository(cryptoCoinApi, exampleDao){
    suspend fun getCryptoCoins(): List<CryptoData> {
        // Simulate a network request with a 1-second delay
        delay(1000)
        // Return mock data
        return generateMockCryptoDataList(20)
    }
}

@HiltViewModel
open class HomeViewModel @Inject constructor(private val cryptoCoinRepository: CryptoCoinRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _isUsdSelected = MutableStateFlow(true)
    open val isUsdSelected: StateFlow<Boolean> = _isUsdSelected.asStateFlow()

    init {
        //fetchCryptoCoinsList()
        //fetchConversionRate()
        fakeFetchCryptoCoinDataFromApi()
        insertAndFetchDataFromDb()
    }

    private fun insertAndFetchDataFromDb() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading // Set loading state
            try {
                withContext(Dispatchers.IO) {
                    insertEntity()
                    val isUsdSelected = cryptoCoinRepository.exampleDao.getExampleData().first().isUsdCurrency
                    Timber.d("is usd =$isUsdSelected")
                }
                //_uiState.value = HomeUiState.Success(cryptoDataList) // Set success state
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error: ${e.message}") // Set error state
            }
        }
    }


    private suspend fun insertEntity() {
        cryptoCoinRepository.exampleDao.insert(UserPreferences(title = "test", description = "test", isUsdCurrency = true))
    }

    open fun changeCurrency(isUsdSelected: Boolean) {
        _isUsdSelected.value = isUsdSelected

        when (_isUsdSelected.value) {
            true -> calculateConversionPrice(true)
            false -> calculateConversionPrice(false)
        }
    }

    private fun calculateConversionPrice(isUsdSelected: Boolean) {
        if (isUsdSelected) {
            // Generate mock data
            val cryptoDataList = generateMockCryptoDataList(isUsd = true)
            _uiState.value = UiState.Success(cryptoDataList) // Set success state
        } else {
            // Generate mock data
            val cryptoDataList = generateMockCryptoDataList(isUsd = false)
            _uiState.value = UiState.Success(cryptoDataList) // Set success state
        }

    }

    private fun fetchConversionRate() {
        viewModelScope.launch {
            val response = cryptoCoinRepository.cryptoCoinApi.getSekConversionRate()

            when (response.isSuccessful) {
                true -> {
                    val currencyResponse: CurrencyResponse = response.body()!!
                    // Process the cryptoDataList
                    Timber.d("conversionRate: ${currencyResponse.usd.sek}")
                    //_uiState.value = UiState.Success(cryptoDataList)
                }

                false -> _uiState.value = UiState.Error(response.message())
            }
        }
    }

    private fun fakeFetchCryptoCoinDataFromApi() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading // Set loading state
            try {
                // Simulate a network request with a 3-second delay
                delay(2000)

                // Generate mock data
                val cryptoDataList = generateMockCryptoDataList(20)

                _uiState.value = UiState.Success(cryptoDataList) // Set success state
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error: ${e.message}") // Set error state
            }
        }
    }


    open fun fetchCryptoCoinsList() {
        viewModelScope.launch {
            val response = cryptoCoinRepository.cryptoCoinApi.getCryptoData()

            when (response.isSuccessful) {
                true -> {
                    val cryptoDataList: List<CryptoData> = response.body()!!
                    // TODO: save to DB here
                    // Process the cryptoDataList
                    //println(cryptoDataList)
                    _uiState.value = UiState.Success(cryptoDataList)
                }

                false -> _uiState.value = UiState.Error(response.message())
            }
        }
    }
}