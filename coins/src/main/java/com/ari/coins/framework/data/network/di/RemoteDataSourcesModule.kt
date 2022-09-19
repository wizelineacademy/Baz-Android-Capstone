package com.ari.coins.framework.data.network.di

import com.ari.coins.data.network.dataSource.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import com.ari.coins.framework.data.network.dataSources.CoinsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourcesModule {

    @Inject
    @Provides
    fun provideCoinsRemoteDatasource(coinsApi: CoinsApi): CoinsRemoteDataSource = CoinsRemoteDataSourceImpl(coinsApi)
}