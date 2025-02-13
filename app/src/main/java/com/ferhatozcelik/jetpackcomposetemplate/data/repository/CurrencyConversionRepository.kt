package com.ferhatozcelik.jetpackcomposetemplate.data.repository

import com.ferhatozcelik.jetpackcomposetemplate.data.remote.CurrencyConversionApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CurrencyConversionRepository @Inject constructor(val currencyConversionApi: CurrencyConversionApi)