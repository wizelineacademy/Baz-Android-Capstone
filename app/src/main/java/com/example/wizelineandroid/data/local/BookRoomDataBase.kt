package com.example.wizelineandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.dao.OrderDao
import com.example.wizelineandroid.data.local.dao.TickerDao
import com.example.wizelineandroid.data.local.entitys.AskEntity
import com.example.wizelineandroid.data.local.entitys.BidsEntity
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.local.entitys.TickerEntity

@Database(
    entities = [BookEntity::class, TickerEntity::class, AskEntity::class, BidsEntity::class],
    version = 10,
    exportSchema = false
)
abstract class BookRoomDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun tickerDao(): TickerDao
    abstract fun orderDao(): OrderDao

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
