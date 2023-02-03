package com.baz.cmv.criptomonedas.coins.di

import android.content.Context
import androidx.room.Room
import com.baz.cmv.criptomonedas.coins.core.Constants
import com.baz.cmv.criptomonedas.coins.data.remote.database.CoinsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomHelper {
    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context : Context) =
        Room.databaseBuilder(context, CoinsDataBase::class.java, Constants.DATABASE_NAME)
            .build()
    }