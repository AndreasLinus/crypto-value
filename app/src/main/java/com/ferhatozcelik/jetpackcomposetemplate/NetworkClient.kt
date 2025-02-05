package com.ferhatozcelik.jetpackcomposetemplate

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkClient {

    private const val TIMEOUT_SECONDS = 30L

    val okHttpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Set the desired logging level
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Add the logging interceptor
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS) // Set connection timeout
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS) // Set read timeout
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS) // Set write timeout
            .build()
    }
}