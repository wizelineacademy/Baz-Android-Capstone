package com.axiasoft.android.zerocoins.db.bitso.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.entity.ExchangeOrderBookEntity

@Dao
interface ExchangeOrderBookDao {
    @Insert
    fun insert(exchangeOrderBook: ExchangeOrderBookEntity)

    @Query("Select * From exchange_order_books")
    fun getAvailableBooks():List<ExchangeOrderBookEntity>
}