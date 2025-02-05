package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cryptoCoinRepository: CryptoCoinRepository) :
    ViewModel() {

    private val TAG = HomeViewModel::class.java.simpleName
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        fetchCryptoCoinsList()
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