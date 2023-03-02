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
    /**
     * It is responsible for providing an instance of the room
     * database with the necessary configuration
     *
     * @param appContext is the application context
     */
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): CRYAppDatabase {
        return Room
            .databaseBuilder(
                appContext,
                CRYAppDatabase::class.java,
                CRYAppDatabase.DB_NAME,
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provides an instance of the interface that controls the books
     * table with their respective queries
     */
    @Singleton
    @Provides
    fun provideBookDao(db: CRYAppDatabase) = db.bookDao()

    /**
     * Provides an instance of the interface that controls the detail
     * table of a specific book with its respective queries.
     */
    @Singleton
    @Provides
    fun provideTickerDao(db: CRYAppDatabase) = db.tickerDao()
}
