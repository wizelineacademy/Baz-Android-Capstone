package com.example.cryptocurrency_challenge.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicker(ticker : TickerEntity)

    @Query("SELECT * FROM ticker_entity WHERE ticker_entity.book = :currency_name")
    suspend fun getTicker(currency_name: String): TickerEntity

}