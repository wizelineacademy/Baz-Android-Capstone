package com.axiasoft.android.zerocoins.db.bitso.dao

import androidx.room.*
import com.axiasoft.android.zerocoins.db.EXCHANGE_ORDER_BOOK_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.entity.ExchangeOrderBookEntity

@Dao
interface ExchangeOrderBookDao {

    @Insert // (onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeOrderBook: ExchangeOrderBookEntity)

    @Query("Select * From $EXCHANGE_ORDER_BOOK_TB_NAME")
    fun getAvailableBooks(): List<ExchangeOrderBookEntity>

    @Query("Delete From $EXCHANGE_ORDER_BOOK_TB_NAME")
    fun deleteAvailableExchangeOrderBooks()
}
