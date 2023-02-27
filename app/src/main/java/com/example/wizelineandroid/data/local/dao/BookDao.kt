package com.example.wizelineandroid.data.local.dao

import androidx.room.*
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.local.entitys.TickerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: List<BookEntity>)

    @Query("SELECT * from book")
    fun getBooks(): Flow<List<BookEntity>>


}