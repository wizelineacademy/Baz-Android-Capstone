package com.example.bazandroidcourse.di

import android.content.Context
import com.example.bazandroidcourse.data.utils.network.NetworkManagerImpl
import com.example.bazandroidcourse.data.utils.network.NetworkManagerInterface
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
    fun providesNetworkManager(@ApplicationContext context: Context): NetworkManagerInterface {
        return NetworkManagerImpl(context)
    }
}
