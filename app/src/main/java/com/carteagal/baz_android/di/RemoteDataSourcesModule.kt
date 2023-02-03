package com.carteagal.baz_android.di

import com.carteagal.baz_android.data.dataSources.CryptoRemoteDataSourceImp
import com.carteagal.baz_android.data.dataSources.CryptoRemoteDataSources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourcesModule {

    @Binds
    abstract fun cryptoRemoteDataSourceModule(cryptoRemoteDataSourcesImp: CryptoRemoteDataSourceImp): CryptoRemoteDataSources
}