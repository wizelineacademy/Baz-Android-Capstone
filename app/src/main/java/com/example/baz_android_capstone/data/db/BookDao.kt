package com.example.baz_android_capstone.data.db // ktlint-disable package-name

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baz_android_capstone.data.models.availableBook.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM book_table")
    fun getBooks(): Flow<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBooks(books: Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteBooks()
}
