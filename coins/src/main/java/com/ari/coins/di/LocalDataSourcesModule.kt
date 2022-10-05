package com.ari.coins.di

import com.ari.coins.data.local.CoinsLocalDataSource
import com.ari.coins.framework.data.local.dataSources.CoinsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Ari Valencia
 * @file LocalDataSourcesModule
 * @description Provide only local data sources
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourcesModule {
    @Binds
    abstract fun provideCoinsLocalDataSource(dataSourceImpl: CoinsLocalDataSourceImpl): CoinsLocalDataSource
}
