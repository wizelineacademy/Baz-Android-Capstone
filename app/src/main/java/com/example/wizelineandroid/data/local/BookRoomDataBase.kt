package com.example.wizelineandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.dao.TickerDao
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.local.entitys.TickerEntity

@Database(entities = [BookEntity::class, TickerEntity::class], version = 3, exportSchema = false)
abstract class BookRoomDataBase: RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun tickerDao(): TickerDao


    companion object {
        @Volatile
        private var INSTANCE: BookRoomDataBase? = null
        fun getDatabase(context: Context): BookRoomDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDataBase::class.java,
                    "books_database"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}