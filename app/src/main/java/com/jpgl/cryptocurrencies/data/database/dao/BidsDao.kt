package com.jpgl.cryptocurrencies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpgl.cryptocurrencies.data.database.entities.BidsEntity

@Dao
interface BidsDao: BaseDao<BidsEntity> {

    @Query("SELECT * FROM table_bids")
    suspend fun getAllBids(): List<BidsEntity>

    @Query("DELETE FROM table_bids")
    suspend fun deleteAllBids()

}