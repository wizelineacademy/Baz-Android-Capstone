package com.javg.cryptocurrencies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.db.remote.CRYAppDatabase
import com.javg.cryptocurrencies.data.model.CRYBookResponse

@Dao
interface CRYBookDao {
    @Query("SELECT * FROM ${CRYAppDatabase.BOOK_TABLE}")
    fun getAllBook(): List<CRYBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(books: List<CRYBookEntity>)
}