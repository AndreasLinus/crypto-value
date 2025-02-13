package com.ferhatozcelik.jetpackcomposetemplate.data.model

// TODO: extend this for generic UiState
sealed class Resource<out T : Any> {
    data object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: Any) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
}