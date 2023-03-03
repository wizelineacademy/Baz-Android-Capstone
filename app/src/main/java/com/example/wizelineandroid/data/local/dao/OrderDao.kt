package com.example.wizelineandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizelineandroid.data.local.entitys.AskEntity
import com.example.wizelineandroid.data.local.entitys.BidsEntity

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAsk(item: List<AskEntity>)

    @Query("SELECT * from ask where id = :id")
    fun getAsk(id: String): List<AskEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBids(item: List<BidsEntity>)

    @Query("SELECT * from bids where id = :id")
    fun getBids(id: String): List<BidsEntity>
}
