package com.capstone.capstonecoins.domain.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.repository.models.BookDetail

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(book: List<Book>)

    @Query("SELECT * FROM Book")
    fun getAvailableLocalBooks(): List<Book>

    @Query("SELECT * FROM BookDetail WHERE book = :book")
    fun getLocalDetailBooks(book: String): BookDetail

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocalDetailBooks(typeCoin: BookDetail)
}