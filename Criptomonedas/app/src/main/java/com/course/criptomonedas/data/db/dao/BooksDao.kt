package com.course.criptomonedas.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.course.criptomonedas.data.db.model.BooksEntity


@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(books: List<BooksEntity>)

    @Query(
        """
            SELECT * FROM currency
        """
    )
    fun getBooks(): List<BooksEntity>
}