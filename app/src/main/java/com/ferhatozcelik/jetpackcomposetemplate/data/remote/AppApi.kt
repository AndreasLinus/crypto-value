package com.ferhatozcelik.jetpackcomposetemplate.data.remote

import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.*

interface AppApi {

    @Headers("Accept: application/json")
    @GET("https://api.wazirx.com/sapi/v1/tickers/24hr")
    suspend fun getCryptoData(): Response<List<CryptoData>>

    @Headers("Accept: application/json")
    @GET("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@2024-03-06/v1/currencies/usd.json")
    suspend fun getSekConversionRate(): Response<CurrencyResponse>

}


