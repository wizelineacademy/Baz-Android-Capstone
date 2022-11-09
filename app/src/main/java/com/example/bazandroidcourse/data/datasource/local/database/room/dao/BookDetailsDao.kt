package com.example.bazandroidcourse.data.datasource.local.database.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity

@Dao
interface BookDetailsDao:GenericDao<BookDetailEntity> {
    @Query("SELECT * FROM book_details ")
    suspend fun getAll():List<BookDetailEntity>

    @Query("SELECT * from book_details where book_id = :bookId")
    suspend fun getBookDetail(bookId:String):BookDetailEntity?

    @Query("DELETE from book_details")
    suspend fun deleteAll()


}