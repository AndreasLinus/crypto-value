package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val exampleRepository: ExampleRepository) : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    fun fetchDataFromApi() {
        viewModelScope.launch {
            val response = exampleRepository.appApi.getCryptoData()
            val cryptoDataList: List<CryptoData> = response.body()!!
            // Process the cryptoDataList
            println(cryptoDataList)
            // TODO: handle failure and error
        }
    }


}