package com.ari.coins.framework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ari.coins.framework.data.local.entities.TickerEntity

/**
 * @author Ari Valencia
 * @file TickerDao
 * @description Dao for ticker with CRUD operations
 */

@Dao
interface TickerDao {

    @Query("SELECT * FROM ticker_table WHERE book = :book")
    suspend fun getTicker(book: String): TickerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicker(data: TickerEntity)

    @Query("DELETE FROM ticker_table WHERE book = :book")
    suspend fun deleteTicker(book: String)
}
