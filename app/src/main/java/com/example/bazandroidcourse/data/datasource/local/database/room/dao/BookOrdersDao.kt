package com.example.bazandroidcourse.data.datasource.local.database.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity

@Dao
interface BookOrdersDao:GenericDao<BookOrderEntity>{
    @Query("SELECT book_id, amount, price, type  FROM book_orders ")
    suspend fun getAll():List<BookOrderEntity>
}