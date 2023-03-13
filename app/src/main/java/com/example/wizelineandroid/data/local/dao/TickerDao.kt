package com.example.wizelineandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizelineandroid.data.local.entitys.TickerEntity

@Dao
interface TickerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTicker(item: TickerEntity)

    @Query("SELECT * from tickers where id = :id")
    fun getTicket(id: String): TickerEntity
}
