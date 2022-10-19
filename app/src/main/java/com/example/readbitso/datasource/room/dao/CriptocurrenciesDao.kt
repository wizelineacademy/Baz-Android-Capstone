package com.example.readbitso.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readbitso.datasource.room.dao.entity.Currencies

@Dao
interface CriptocurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(activeTokens: List<Currencies>)

    @Query("SELECT * FROM Currencies")
    suspend fun getAll(): List<Currencies>
}
