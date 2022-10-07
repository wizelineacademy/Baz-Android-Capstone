package com.example.readbitso.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readbitso.datasource.room.dao.entity.OperationsTrades

@Dao
interface TradesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(operations: List<OperationsTrades>)

    @Query("SELECT * FROM OperationsTrades")
    suspend fun getAll(): List<OperationsTrades>
}
