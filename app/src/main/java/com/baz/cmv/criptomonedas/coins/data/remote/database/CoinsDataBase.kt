package com.baz.cmv.criptomonedas.coins.data.remote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baz.cmv.criptomonedas.coins.data.remote.database.dao.CoinsDao
import com.baz.cmv.criptomonedas.coins.data.remote.entities.CoinsEntity

@Database(entities = [CoinsEntity::class],version = 1)
abstract class CoinsDataBase : RoomDatabase() {
    abstract fun coinDao(): CoinsDao
   // GetCoinsDao(): CoinsDao

    companion object{

        @Volatile //resultados visibles para otros procesos
        private var INSTANCE: CoinsDataBase? = null

        fun getDatabase(context: Context): CoinsDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CoinsDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                CoinsDataBase::class.java,
                "coins_table"
            )
                .build()
        }
    }

}