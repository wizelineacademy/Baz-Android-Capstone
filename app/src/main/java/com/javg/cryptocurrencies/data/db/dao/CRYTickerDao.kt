package com.javg.cryptocurrencies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javg.cryptocurrencies.data.db.entity.CRYDetailBookEntity
import com.javg.cryptocurrencies.data.db.remote.CRYAppDatabase

@Dao
interface CRYTickerDao {
    @Query("SELECT * FROM ${CRYAppDatabase.DETAIL_BOOK_TABLE} WHERE book = :book")
    fun findById(book: String): CRYDetailBookEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(ticker: CRYDetailBookEntity)

    @Query("UPDATE ${CRYAppDatabase.DETAIL_BOOK_TABLE} SET askList = :ask, bidsList = :bids WHERE book = :book")
    fun update(ask: String, bids: String, book: String)
}
