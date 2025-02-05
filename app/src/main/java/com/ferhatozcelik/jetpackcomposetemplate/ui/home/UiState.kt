package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData

sealed interface UiState {
    data object Loading : UiState
    data class Success(val cryptoDataList: List<CryptoData>) : UiState
    data class Error(val message: String) : UiState
}