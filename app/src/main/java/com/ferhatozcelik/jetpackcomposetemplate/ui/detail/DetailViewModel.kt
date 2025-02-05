package com.ferhatozcelik.jetpackcomposetemplate.ui.detail

import androidx.lifecycle.ViewModel
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val cryptoCoinRepository: CryptoCoinRepository) : ViewModel() {
    private val TAG = DetailViewModel::class.java.simpleName


}