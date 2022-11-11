package com.course.criptomonedas.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.course.criptomonedas.data.db.dao.BooksDao
import com.course.criptomonedas.data.db.model.BooksEntity

@Database(entities = [BooksEntity::class], version = 1, exportSchema = false)
abstract class DataBaseCriptos : RoomDatabase() {
    abstract fun booksDao(): BooksDao

    companion object {

        @Volatile
        private var INSTANCE: DataBaseCriptos? = null

        fun getDatabase(context: Context): DataBaseCriptos {
            if (INSTANCE == null) {
                synchronized(DataBaseCriptos::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DataBaseCriptos::class.java,
                            "criptos_database"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}