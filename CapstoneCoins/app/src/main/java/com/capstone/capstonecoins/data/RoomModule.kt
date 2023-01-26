package com.capstone.capstonecoins.data

import android.content.Context
import androidx.room.Room
import com.capstone.capstonecoins.data.repository.database.BooksDB
import com.capstone.capstonecoins.domain.api.BooksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BooksDB {
        return Room
            .databaseBuilder(
                context,
                BooksDB::class.java,
                "BooksDatabase"
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideBooksDao(booksDB: BooksDB): BooksDao {
        return booksDB.booksDao()
    }
}