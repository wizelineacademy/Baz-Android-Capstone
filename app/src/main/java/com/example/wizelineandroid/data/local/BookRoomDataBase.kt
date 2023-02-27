package com.example.wizelineandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 2, exportSchema = false)
abstract class BookRoomDataBase: RoomDatabase() {

    abstract fun itemDao(): BookDao

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