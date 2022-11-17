package com.wizeline.criptocurrency.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wizeline.criptocurrency.data.database.dao.CryptoCurrencyDao
import com.wizeline.criptocurrency.data.database.entities.AsksEntity
import com.wizeline.criptocurrency.data.database.entities.AvailableBookEntity
import com.wizeline.criptocurrency.data.database.entities.BidsEntity
import com.wizeline.criptocurrency.data.database.entities.TickerEntity

@Database(entities = [AvailableBookEntity::class, TickerEntity::class, AsksEntity::class, BidsEntity::class], version = 1)
abstract class CriptoCurrencyDB : RoomDatabase() {
    abstract fun getCriptoCurrencyDao(): CryptoCurrencyDao
}
