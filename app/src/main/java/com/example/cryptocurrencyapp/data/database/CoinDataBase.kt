package com.example.cryptocurrencyapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptocurrencyapp.data.database.dao.CryptoDao
import com.example.cryptocurrencyapp.data.database.entities.AskEntity
import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity
import com.example.cryptocurrencyapp.data.database.entities.BidEntity
import com.example.cryptocurrencyapp.data.database.entities.TickerEntity

@Database (entities = [AvailableBookEntity::class, TickerEntity::class, AskEntity::class, BidEntity::class], version = 3)
abstract class CoinDataBase : RoomDatabase() {

    abstract fun getCoinDao(): CryptoDao
}