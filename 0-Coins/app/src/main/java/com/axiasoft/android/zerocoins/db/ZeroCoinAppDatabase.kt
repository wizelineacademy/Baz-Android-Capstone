package com.axiasoft.android.zerocoins.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axiasoft.android.zerocoins.db.bitso.dao.ExchangeOrderBookDao
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.entity.ExchangeOrderBookEntity


@Database(entities = [ExchangeOrderBookEntity::class], version = 1)
abstract class ZeroCoinAppDatabase : RoomDatabase() {
    abstract fun bookDao(): ExchangeOrderBookDao
}