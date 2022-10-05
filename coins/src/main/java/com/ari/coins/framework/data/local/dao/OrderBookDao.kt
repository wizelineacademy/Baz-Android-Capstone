package com.ari.coins.framework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ari.coins.framework.data.local.entities.OrderBookEntity

/**
 * @author Ari Valencia
 * @file OrderBookDao
 * @description Dao for order book with CRUD operations
 */

@Dao
interface OrderBookDao {

    @Query("SELECT * FROM order_book_table WHERE book = :book")
    suspend fun getOrderBook(book: String): OrderBookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderBook(data: OrderBookEntity)

    @Query("DELETE FROM order_book_table WHERE book = :book")
    suspend fun deleteOrderBook(book: String)
}
