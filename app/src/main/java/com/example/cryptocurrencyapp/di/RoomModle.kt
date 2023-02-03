package com.example.cryptocurrencyapp.di

import android.content.Context
import androidx.room.Room
import com.example.cryptocurrencyapp.data.database.CoinDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModle {

    const val COIN_DATABASE_NAME = "coin_data_basee"

    @Singleton
    @Provides
    fun providerRoom (@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CoinDataBase ::class.java, COIN_DATABASE_NAME)
            .build()


    @Singleton
    @Provides
    fun providerCoinDao (bd: CoinDataBase) = bd.getCoinDao()

}