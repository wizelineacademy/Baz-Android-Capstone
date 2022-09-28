package com.ari.coins.framework.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ari.coins.framework.data.local.dao.AvailableBookDao
import com.ari.coins.framework.data.local.entities.AvailableBookEntity
import com.ari.coins.framework.data.local.entities.converters.FeesConverter

@Database(
    entities = [AvailableBookEntity::class],
    version = 1
)
@TypeConverters(FeesConverter::class)
abstract class AvailableBookDB: RoomDatabase() {
    abstract fun getAvailableBookDao(): AvailableBookDao
}