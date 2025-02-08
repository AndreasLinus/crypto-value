package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CurrencyResponse
import com.ferhatozcelik.jetpackcomposetemplate.data.model.UiState
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cryptoCoinRepository: CryptoCoinRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _isUsdSelected = MutableStateFlow(true)
    val isUsdSelected: StateFlow<Boolean> = _isUsdSelected.asStateFlow()

    init {
        //fetchCryptoCoinsList()
        //fetchConversionRate()
        fakeFetchCryptoCoinDataFromApi()
    }

    fun changeCurrency(isUsdSelected: Boolean) {
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
                delay(3000)

                // Generate mock data
                val cryptoDataList = generateMockCryptoDataList(20)

                _uiState.value = UiState.Success(cryptoDataList) // Set success state
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error: ${e.message}") // Set error state
            }
        }
    }


    fun fetchCryptoCoinsList() {
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