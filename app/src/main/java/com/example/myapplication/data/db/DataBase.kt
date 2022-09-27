package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.db.dao.CriptoCurrencyDAO
import com.example.myapplication.model.CriptoCurrency

//@Database(entities = [CriptoCurrency::class, OtraTabla::class], version = 1, exportSchema = false)
@Database(entities = [CriptoCurrency::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun criptoCurrencyDAO(): CriptoCurrencyDAO
    //abstract fun employeeDAO(): EmployeeDAO

    companion object {

        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            if (INSTANCE == null) {
                synchronized(DataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, DataBase::class.java, "criptoCurrency").build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}