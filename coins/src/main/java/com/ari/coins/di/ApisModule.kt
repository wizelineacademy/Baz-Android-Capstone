package com.ari.coins.di

import com.ari.coins.framework.data.network.apis.CoinsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author Ari Valencia
 * @file ApisModule
 * @description Provide api services with custom retrofit
 */

@Module
@InstallIn(SingletonComponent::class)
class ApisModule {

    @Inject
    @Provides
    fun provideCoinsApi(retrofit: Retrofit): CoinsApi = retrofit.create(CoinsApi::class.java)
}
