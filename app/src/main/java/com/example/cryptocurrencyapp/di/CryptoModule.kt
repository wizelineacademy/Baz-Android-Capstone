package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.data.api.CryptoApi
import com.example.cryptocurrencyapp.utils.CryptoConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object CryptoModule {

    @Singleton
    @Provides
    fun proverRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(CryptoConstants.BITSO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

    }

    @Singleton
    @Provides
    fun provideCryptoApi(retrofit: Retrofit): CryptoApi {
       return retrofit.create( CryptoApi::class.java)
    }
}