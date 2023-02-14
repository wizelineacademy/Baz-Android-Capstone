package com.javg.cryptocurrencies.config

import android.content.Context
import androidx.room.Room
import com.javg.cryptocurrencies.data.db.remote.CRYAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CRYRoomHelper {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): CRYAppDatabase {
        return Room
            .databaseBuilder(
                appContext,
                CRYAppDatabase::class.java,
                CRYAppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBookDao(db: CRYAppDatabase) = db.bookDao()

    @Singleton
    @Provides
    fun provideTickerDao(db: CRYAppDatabase) = db.tickerDao()
}