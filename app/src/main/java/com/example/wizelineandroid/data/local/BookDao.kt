package com.example.wizelineandroid.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: List<BookEntity>)

    @Query("SELECT * from book ORDER BY name ASC")
    fun getBooks(): Flow<List<BookEntity>>
}