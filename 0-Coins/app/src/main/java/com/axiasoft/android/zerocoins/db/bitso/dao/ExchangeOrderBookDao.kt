package com.axiasoft.android.zerocoins.db.bitso.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.axiasoft.android.zerocoins.db.EXCHANGE_ORDER_BOOK_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.entity.ExchangeOrderBookEntity

@Dao
interface ExchangeOrderBookDao {
    @Insert
    fun insert(exchangeOrderBook: ExchangeOrderBookEntity)

    @Query("Select * From $EXCHANGE_ORDER_BOOK_TB_NAME")
    fun getAvailableBooks():List<ExchangeOrderBookEntity>

    @Query("Delete From $EXCHANGE_ORDER_BOOK_TB_NAME")
    fun deleteAvailableExchangeOrderBooks()
}