package com.javg.cryptocurrencies.data.db.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javg.cryptocurrencies.data.db.dao.CRYBookDao
import com.javg.cryptocurrencies.data.db.dao.CRYTickerDao
import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.db.entity.CRYDetailBookEntity

@Database(entities = [CRYBookEntity::class, CRYDetailBookEntity::class], version = 4)
abstract class CRYAppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "database_crypto_book"
        const val BOOK_TABLE = "book_table"
        const val DETAIL_BOOK_TABLE = "detail_book_table"
    }
    abstract fun bookDao(): CRYBookDao

    abstract fun tickerDao(): CRYTickerDao
}
