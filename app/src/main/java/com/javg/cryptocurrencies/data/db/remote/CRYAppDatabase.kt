package com.javg.cryptocurrencies.data.db.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javg.cryptocurrencies.data.db.dao.CRYBookDao
import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity

@Database(entities = [CRYBookEntity::class], version = 1)
abstract class CRYAppDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME = "database_crypto_book"
        const val BOOK_TABLE = "book_table"
    }
    abstract fun bookDao(): CRYBookDao
}