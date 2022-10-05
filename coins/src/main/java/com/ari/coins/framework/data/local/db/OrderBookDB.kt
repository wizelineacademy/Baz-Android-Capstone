package com.ari.coins.framework.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ari.coins.framework.data.local.dao.OrderBookDao
import com.ari.coins.framework.data.local.entities.OrderBookEntity
import com.ari.coins.framework.data.local.entities.converters.AskConverter
import com.ari.coins.framework.data.local.entities.converters.BidConverter

/**
 * @author Ari Valencia
 * @file OrderBookDB
 * @description Order book DB using Room
 */

@Database(
    entities = [OrderBookEntity::class],
    version = 1
)
@TypeConverters(AskConverter::class, BidConverter::class)
abstract class OrderBookDB : RoomDatabase() {
    abstract fun getOrderBookDao(): OrderBookDao
}
