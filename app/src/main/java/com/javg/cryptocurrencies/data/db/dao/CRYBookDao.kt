package com.javg.cryptocurrencies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javg.cryptocurrencies.data.model.CRYBookResponse

@Dao
interface CRYBookDao {
    @Query("SELECT * FROM CRYBookResponse")
    fun getAllBook(): List<CRYBookResponse>

    @Insert
    fun insertAllBook(vararg book: CRYBookResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(books: List<CRYBookResponse>)
}