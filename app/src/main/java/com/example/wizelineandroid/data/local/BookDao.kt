package com.example.wizelineandroid.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: List<BookEntity>)

    @Update
    suspend fun update(item: BookEntity)

    @Delete
    suspend fun delete(item: BookEntity)

    @Query("SELECT * from book")
    fun getBooks(): Flow<List<BookEntity>>
}