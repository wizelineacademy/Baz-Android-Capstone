package com.proyect.cursowizline.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.proyect.cursowizline.database.dao.CryptoDao
import com.proyect.cursowizline.database.entities.CryptoEntity

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptoDatabase: RoomDatabase() {

    abstract fun getCryptoDao():CryptoDao
}