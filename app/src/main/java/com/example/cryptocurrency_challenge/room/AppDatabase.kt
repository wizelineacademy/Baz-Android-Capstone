package com.example.cryptocurrency_challenge.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [TickerEntity::class], version = 1, exportSchema = false )
abstract class AppDatabase: RoomDatabase() {

    abstract fun roomEntityDao(): RoomEntityDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "room_db")
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}