package com.axiasoft.android.zerocoins.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axiasoft.android.zerocoins.application.ZeroCoinsApplication
import com.axiasoft.android.zerocoins.db.bitso.dao.AskDao
import com.axiasoft.android.zerocoins.db.bitso.dao.BidsDao
import com.axiasoft.android.zerocoins.db.bitso.dao.ExchangeOrderBookDao
import com.axiasoft.android.zerocoins.db.bitso.dao.TickerDao
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.entity.ExchangeOrderBookEntity
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.open_orders_book.entity.AskEntity
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.open_orders_book.entity.BidsEntity
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.ticker.entity.TickerEntity

const val DB_NAME_Z0 = "zero_coins_db"
const val EXCHANGE_ORDER_BOOK_TB_NAME = "exchange_order_books"
const val ASK_TB_NAME = "ask_table"
const val BIDS_TB_NAME = "bids_table"
const val TICKER_TB_NAME = "ticker_table"

@Database(
    entities = [
        ExchangeOrderBookEntity::class,
        AskEntity::class,
        BidsEntity::class,
        TickerEntity::class,
    ],
    version = 1,
)
abstract class ZeroCoinAppDatabase : RoomDatabase() {
    abstract fun bookDao(): ExchangeOrderBookDao
    abstract fun askDao(): AskDao
    abstract fun bidDao(): BidsDao
    abstract fun tickerDao(): TickerDao
}

private lateinit var INSTANCE: ZeroCoinAppDatabase

fun getDatabase(
    context: Context = ZeroCoinsApplication.appContext,
): ZeroCoinAppDatabase {
    synchronized(ZeroCoinAppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ZeroCoinAppDatabase::class.java,
                DB_NAME_Z0,
            ).build()
        }
    }
    return INSTANCE
}
