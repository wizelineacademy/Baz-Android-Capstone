package com.example.bazandroidcourse.data.datasource.local.database.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity

@Dao
interface BookDetailsDao:GenericDao<BookDetailEntity> {
    @Query("SELECT book_id, amount, price, type  FROM book_orders ")
    suspend fun getAll():List<BookDetailEntity>

    @Query("SELECT book_id, volume, low, high, last from book_details where book_id = :bookId")
    suspend fun getBookDetail(bookId:String):BookDetailEntity
}