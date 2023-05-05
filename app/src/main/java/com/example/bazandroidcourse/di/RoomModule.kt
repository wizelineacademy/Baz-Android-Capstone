package com.example.bazandroidcourse.di

import android.content.Context
import androidx.room.Room
import com.example.bazandroidcourse.data.datasource.local.database.room.core.AppDataBase
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BookDetailsDao
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BookOrdersDao
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BooksDao
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
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "com_mx_baz_android_course_db"
    ).build()

    @Singleton
    @Provides
    fun provideBookDao(db: AppDataBase): BooksDao {
        return db.booksDao()
    }

    @Singleton
    @Provides
    fun provideBookDetailDao(db: AppDataBase): BookDetailsDao {
        return db.bookDetailsDao()
    }

    @Singleton
    @Provides
    fun provideOrdersBookDao(db: AppDataBase): BookOrdersDao {
        return db.bookOrdersDao()
    }
}
