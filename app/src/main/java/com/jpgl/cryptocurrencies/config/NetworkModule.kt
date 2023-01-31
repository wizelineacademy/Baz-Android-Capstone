package com.jpgl.cryptocurrencies.config

import com.jpgl.cryptocurrencies.data.webservice.CryptoApiClient
import com.jpgl.cryptocurrencies.utils.Constants.URL_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient( retrofit: Retrofit ) : CryptoApiClient{
        return retrofit.create(CryptoApiClient::class.java)
    }

}