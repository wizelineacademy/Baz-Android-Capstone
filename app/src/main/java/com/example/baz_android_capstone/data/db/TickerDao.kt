package com.example.baz_android_capstone.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baz_android_capstone.data.models.ticker.Ticker
import kotlinx.coroutines.flow.Flow

@Dao
interface TickerDao {
    @Query("SELECT * FROM ticker_table")
    fun getTicker(): Flow<Ticker>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTicker(ticker: Ticker)

    @Query("DELETE FROM ticker_table")
    suspend fun deleteTicker()
}
