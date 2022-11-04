package com.example.cryptocurrencyapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptocurrencyapp.data.database.dao.CryptoDao
import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity

@Database (entities = [AvailableBookEntity::class], version = 1)
abstract class CoinDataBase : RoomDatabase() {

    abstract fun getCoinDao(): CryptoDao
}