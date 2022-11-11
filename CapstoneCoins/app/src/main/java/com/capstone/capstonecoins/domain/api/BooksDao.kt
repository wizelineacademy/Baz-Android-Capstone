package com.capstone.capstonecoins.domain.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.capstone.capstonecoins.data.models.availablebooks.Payload

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllBooks(payload: List<Payload>)
}