package com.ferhatozcelik.jetpackcomposetemplate.data.remote

import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import retrofit2.Response
import retrofit2.http.*

interface AppApi {

    @Headers("Accept: application/json")
    @GET(BASE_PATH)
    suspend fun getCryptoData(): Response<List<CryptoData>>

    companion object {
        const val BASE_PATH = "https://api.wazirx.com/sapi/v1/tickers/24hr"
        //const val BASE_PATH = "api.wazirx.com/sapi/v1/tickers/{hours}"
    }

    // TODO: add query parameter for hours to fetch
    /*@Headers("Accept: application/json")
    @GET(BASE_PATH)
    suspend fun getExampleResult(
        @Query("hour") hours: String
    ): Response<List<CryptoData>>*/
}


