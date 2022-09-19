package com.ari.coins.framework.data.network.di

import com.ari.coins.framework.data.network.apis.CoinsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ApisModule {

    @Inject
    @Provides
    fun provideCoinsApi(retrofit: Retrofit): CoinsApi = retrofit.create(CoinsApi::class.java)

}