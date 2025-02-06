package com.ferhatozcelik.jetpackcomposetemplate.data.model

sealed interface UiState {
    data object Loading : UiState
    data class Success(val cryptoDataList: List<CryptoData>) : UiState
    data class Error(val message: String) : UiState
}