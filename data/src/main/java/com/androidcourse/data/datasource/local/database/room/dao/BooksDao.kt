package com.androidcourse.data.datasource.local.database.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.androidcourse.data.datasource.local.database.room.entities.BookEntity

@Dao
interface BooksDao : GenericDao<BookEntity> {
    @Query("SELECT * from books")
    suspend fun getAll(): List<BookEntity>

    @Query("DELETE from books")
    suspend fun deleteAll()
}
