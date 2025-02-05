package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cryptoCoinRepository: CryptoCoinRepository) : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    fun fetchCryptoCoinsList() {
        viewModelScope.launch {
            val response = cryptoCoinRepository.cryptoCoinApi.getCryptoData()
            val cryptoDataList: List<CryptoData> = response.body()!!
            // Process the cryptoDataList
            println(cryptoDataList)
            // TODO: handle failure and error
        }
    }


}