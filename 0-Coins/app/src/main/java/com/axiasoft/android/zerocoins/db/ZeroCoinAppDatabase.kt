package com.axiasoft.android.zerocoins.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axiasoft.android.zerocoins.application.ZeroCoinsApplication
import com.axiasoft.android.zerocoins.db.bitso.dao.ExchangeOrderBookDao
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.entity.ExchangeOrderBookEntity

const val DB_NAME_Z0 = "zero_coins_db"
const val EXCHANGE_ORDER_BOOK_TB_NAME = "exchange_order_books"

@Database(entities = [ExchangeOrderBookEntity::class], version = 1)
abstract class ZeroCoinAppDatabase : RoomDatabase() {
    abstract fun bookDao(): ExchangeOrderBookDao
}

private lateinit var INSTANCE: ZeroCoinAppDatabase

fun getDatabase(
    context: Context = ZeroCoinsApplication.appContext
): ZeroCoinAppDatabase {
    synchronized(ZeroCoinAppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ZeroCoinAppDatabase::class.java,
                DB_NAME_Z0
            ).build()
        }
    }
    return INSTANCE
}