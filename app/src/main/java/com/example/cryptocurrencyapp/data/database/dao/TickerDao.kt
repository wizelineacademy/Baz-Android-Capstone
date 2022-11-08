package com.example.cryptocurrencyapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity
import com.example.cryptocurrencyapp.data.database.entities.TickerEntity

@Dao
interface TickerDao {

    @Query("SELECT * FROM ticker_table WHERE book LIKE :book")
    suspend fun getickerBD(book: String): TickerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickerBD (ticker: TickerEntity)
}