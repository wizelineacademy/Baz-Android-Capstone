package com.baz.cmv.criptomonedas.coins.di

import android.provider.SyncStateContract
import com.baz.cmv.criptomonedas.coins.core.Constants
import com.baz.cmv.criptomonedas.coins.data.remote.network.CoinsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitHelper {

    @Singleton
    @Provides
    fun provideCoins(builder: Retrofit.Builder): CoinsService {
        return builder
            .build()
            .create(CoinsService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
    }
}