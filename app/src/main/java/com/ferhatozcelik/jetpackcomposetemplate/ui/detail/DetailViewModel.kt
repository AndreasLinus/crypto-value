package com.ferhatozcelik.jetpackcomposetemplate.ui.detail

import androidx.lifecycle.ViewModel
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinValueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val cryptoCoinValueRepository: CryptoCoinValueRepository) : ViewModel() {
    private val TAG = DetailViewModel::class.java.simpleName


}