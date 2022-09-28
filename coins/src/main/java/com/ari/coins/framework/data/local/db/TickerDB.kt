package com.ari.coins.framework.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ari.coins.framework.data.local.dao.TickerDao
import com.ari.coins.framework.data.local.entities.TickerEntity

@Database(
    entities = [TickerEntity::class],
    version = 1
)
abstract class TickerDB: RoomDatabase() {
    abstract fun getTickerDao(): TickerDao
}
