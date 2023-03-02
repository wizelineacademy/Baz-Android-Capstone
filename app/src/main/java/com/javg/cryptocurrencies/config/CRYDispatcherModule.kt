package com.javg.cryptocurrencies.config

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CRYDispatcherModule {

    /**
     * Provides the trigger where the task will be executed
     */
    @Provides
    @Singleton
    fun providerDispatcher() = Dispatchers.IO
}
