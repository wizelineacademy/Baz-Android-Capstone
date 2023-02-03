package com.jpgl.cryptocurrencies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpgl.cryptocurrencies.data.database.entities.BookEntity

@Dao
interface BookDao: BaseDao<BookEntity> {

    @Query("SELECT * FROM table_book")
    suspend fun getAllAvailableBooks(): List<BookEntity>

    @Query("DELETE FROM table_book")
    suspend fun deleteAllAvailableBooks()

}