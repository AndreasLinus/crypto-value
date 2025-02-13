package com.ferhatozcelik.jetpackcomposetemplate.di

import com.ferhatozcelik.jetpackcomposetemplate.data.remote.CryptoCoinValueApi
import com.ferhatozcelik.jetpackcomposetemplate.data.remote.CurrencyConversionApi
import com.ferhatozcelik.jetpackcomposetemplate.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    @Provides
    @Singleton
    fun provideAppApi(retrofit: Retrofit): CryptoCoinValueApi {
        return retrofit.create(CryptoCoinValueApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyConversionApi(retrofit: Retrofit): CurrencyConversionApi {
        return retrofit.create(CurrencyConversionApi::class.java)
    }

}