package com.jpgl.cryptocurrencies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpgl.cryptocurrencies.data.database.entities.TickerEntity

@Dao
interface TickerDao: BaseDao<TickerEntity> {

    @Query("SELECT * FROM table_ticker")
    suspend fun getAllTicker(): TickerEntity?

    @Query("DELETE FROM table_ticker")
    suspend fun deleteAllTicker()

}