package com.ari.coins.di

import com.ari.coins.data.network.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.dataSources.CoinsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Ari Valencia
 * @file RemoteDataSourcesModule
 * @description Provide only remote data sources
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourcesModule {
    @Binds
    abstract fun provideCoinsRemoteDatasource(dataSourceImpl: CoinsRemoteDataSourceImpl): CoinsRemoteDataSource
}
