package com.example.myapplication.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.CriptoCurrency

@Dao
interface CriptoCurrencyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(items: CriptoCurrency)

    @Query("SELECT * FROM currency")
    fun getCriptoList(): List<CriptoCurrency>

    @Query("SELECT * FROM currency")
    fun getCriptoListLiveData(): LiveData<CriptoCurrency>
}