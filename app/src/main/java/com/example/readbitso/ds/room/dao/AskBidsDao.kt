package com.example.readbitso.ds.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readbitso.ds.room.dao.entity.Operationstrades
import kotlinx.coroutines.flow.Flow

@Dao
interface AskBidsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(usuario: List<Operationstrades>)


    @Query("SELECT * FROM AskBids")
    fun getAll(): Flow<List<Operationstrades>>


}
