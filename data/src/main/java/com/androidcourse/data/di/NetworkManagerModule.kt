package com.androidcourse.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkManagerModule {
    @Provides
    @Singleton
    fun providesNetworkManager(@ApplicationContext context: Context): com.androidcourse.data.utils.network.NetworkManagerInterface {
        return com.androidcourse.data.utils.network.NetworkManagerImpl(context)
    }
}
