package com.example.readbitso.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readbitso.datasource.room.dao.entity.AskBids

@Dao
interface AskBidsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(askBids: List<AskBids>)

    @Query("SELECT * FROM AskBids")
    suspend fun getAll(): List<AskBids>
}
