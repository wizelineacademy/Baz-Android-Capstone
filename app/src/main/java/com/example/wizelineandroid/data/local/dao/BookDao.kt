package com.example.wizelineandroid.data.local.dao

import androidx.room.*
import com.example.wizelineandroid.data.local.entitys.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: List<BookEntity>)

    @Query("SELECT * from book")
    fun getBooks(): List<BookEntity>
}
