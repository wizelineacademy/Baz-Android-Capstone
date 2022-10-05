package com.ari.coins.framework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ari.coins.framework.data.local.entities.AvailableBookEntity

/**
 * @author Ari Valencia
 * @file AvailableBookDao
 * @description Dao for available books with CRUD operations
 */

@Dao
interface AvailableBookDao {

    @Query("SELECT * FROM available_book_table")
    suspend fun getAllAvailableBooks(): List<AvailableBookEntity>

    @Query("SELECT * FROM available_book_table WHERE book = :book")
    suspend fun getAvailableBook(book: String): AvailableBookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvailableBook(data: AvailableBookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvailableBooks(list: List<AvailableBookEntity>)

    @Query("DELETE FROM available_book_table")
    suspend fun clearTable()
}
