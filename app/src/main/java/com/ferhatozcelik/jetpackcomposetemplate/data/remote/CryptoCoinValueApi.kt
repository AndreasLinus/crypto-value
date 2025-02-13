package com.ferhatozcelik.jetpackcomposetemplate.data.remote

import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import retrofit2.Response
import retrofit2.http.*

interface CryptoCoinValueApi {

    @Headers("Accept: application/json")
    @GET("https://api.wazirx.com/sapi/v1/tickers/24hr")
    suspend fun getCryptoData(): Response<List<CryptoData>>

}


