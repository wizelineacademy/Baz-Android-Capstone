package com.example.bazandroidcourse.data.datasource.local.database.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity

@Dao
interface BookOrdersDao : GenericDao<BookOrderEntity> {
    @Query("SELECT book_id,book, amount, price, type  FROM book_orders ")
    suspend fun getAll(): List<BookOrderEntity>

    @Query("SELECT book_id,book, amount, price, type  FROM book_orders  where book = :id")
    suspend fun getAll(id: String): List<BookOrderEntity>

    @Query("DELETE from book_orders")
    suspend fun deleteAll()

    @Query("DELETE from book_orders where book = :book")
    suspend fun deleteAll(book: String)
}
