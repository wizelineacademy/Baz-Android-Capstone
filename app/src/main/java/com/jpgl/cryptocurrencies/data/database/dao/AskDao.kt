package com.jpgl.cryptocurrencies.data.database.dao


import androidx.room.Dao
import androidx.room.Query
import com.jpgl.cryptocurrencies.data.database.entities.AsksEntity
import com.jpgl.cryptocurrencies.data.database.entities.BidsEntity

@Dao
interface AsksDao: BaseDao<AsksEntity> {

    @Query("SELECT * FROM table_asks")
    suspend fun getAllAsks(): List<AsksEntity>

    @Query("DELETE FROM table_asks")
    suspend fun deleteAllAsks()

}