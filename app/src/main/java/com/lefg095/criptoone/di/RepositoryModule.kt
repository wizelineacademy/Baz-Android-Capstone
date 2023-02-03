package com.lefg095.criptoone.di

import com.lefg095.criptoone.data.BookRepository
import com.lefg095.criptoone.data.OrderRepository
import com.lefg095.criptoone.data.interfaces.IBookRepository
import com.lefg095.criptoone.data.interfaces.ITickerRepository
import com.lefg095.criptoone.data.TickerRepository
import com.lefg095.criptoone.data.interfaces.IOrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideBookRepository(apiService: ApiService): IBookRepository = BookRepository(apiService)


    @Singleton
    @Provides
    fun provideTickerRepository(apiService: ApiService): ITickerRepository = TickerRepository(apiService)

    @Singleton
    @Provides
    fun provideOrderRepository(apiService: ApiService): IOrderRepository = OrderRepository(apiService)
}