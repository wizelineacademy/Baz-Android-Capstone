package com.example.cryptocurrency_challenge.di

import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import com.example.cryptocurrency_challenge.data.network.NetworkDataSourceImpl
import com.example.cryptocurrency_challenge.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptocurrencyModule {

    @Binds
    abstract fun providesAvailableBooksRepository(availableBooksrepository: AvailableBooksRepositoryImpl): AvailableBooksRepository

    @Binds
    abstract fun providesTickerRepository(tickerRepository: TickerRepositoryImpl): TickerRepository

    @Binds
    abstract fun providesOrderBookRepository(orderBookRepository: OrderBookRepositoryImpl): OrderBookRepository

    @Binds
    abstract fun providesNetworkDataSource(retrofitClientImpl: NetworkDataSourceImpl): NetworkDataSource

}

