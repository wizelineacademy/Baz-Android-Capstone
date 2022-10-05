package com.proyect.cursowizline.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyect.cursowizline.database.entities.CryptoEntity

@Dao
interface CryptoDao {

    @Query("SELECT * FROM crypto_table ORDER BY book DESC")
    suspend fun getAllCrypto():List<CryptoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books:List<CryptoEntity>)

    @Query("DELETE FROM crypto_table")
    suspend fun deleteAllCrypto()
}