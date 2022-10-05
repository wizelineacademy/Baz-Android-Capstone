package com.ari.coins.di

import com.ari.core.client.CryptoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Ari Valencia
 * @file RetrofitModule
 * @description Provide only custom retrofit
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = CryptoRetrofit.retrofit
}
