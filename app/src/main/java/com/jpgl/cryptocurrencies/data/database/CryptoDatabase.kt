package com.jpgl.cryptocurrencies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpgl.cryptocurrencies.data.database.dao.BidsDao
import com.jpgl.cryptocurrencies.data.database.dao.BookDao
import com.jpgl.cryptocurrencies.data.database.dao.TickerDao
import com.jpgl.cryptocurrencies.data.database.entities.BidsEntity
import com.jpgl.cryptocurrencies.data.database.entities.BookEntity
import com.jpgl.cryptocurrencies.data.database.entities.TickerEntity
import com.jpgl.cryptocurrencies.data.database.entities.AsksEntity
import com.jpgl.cryptocurrencies.data.database.dao.AsksDao

//@Database( entities = [BookEntity::class, BidsEntity::class], version = 1)
@Database( entities = [BookEntity::class, BidsEntity::class, AsksEntity::class, TickerEntity::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao
    abstract fun getBidsDao(): BidsDao
    abstract fun getAsksDao(): AsksDao
    abstract fun getTickerDao(): TickerDao
}