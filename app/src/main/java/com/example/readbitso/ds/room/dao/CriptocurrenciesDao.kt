package com.example.readbitso.ds.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readbitso.ds.room.dao.entity.Currencies
import kotlinx.coroutines.flow.Flow

@Dao
interface CriptocurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(usuario: List<Currencies>)


    @Query("SELECT * FROM Currencies")
    suspend fun getAll(): Flow<List<Currencies>>

}