package com.example.baz_android_capstone.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM order_table")
    fun getOrders(): Flow<OrderBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrders(orderBook: OrderBook)

    @Query("DELETE FROM order_table")
    suspend fun deleteOrders()
}
