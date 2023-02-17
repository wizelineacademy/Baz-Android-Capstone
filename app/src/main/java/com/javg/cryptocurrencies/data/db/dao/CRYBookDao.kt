package com.javg.cryptocurrencies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.db.remote.CRYAppDatabase

/**
 * @author Juan Vera Gomez
 * Date modified 13/02/2023
 *
 * Contains the functions for the crud database of books
 *
 * @since 2.0
 */
@Dao
interface CRYBookDao {

    /**
     * Returns a list of books from the books table saved in the database
     */
    @Query("SELECT * FROM ${CRYAppDatabase.BOOK_TABLE}")
    fun getAllBook(): List<CRYBookEntity>

    /**
     * It is in charge of saving the entire list of books obtained remotely from the api
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(books: List<CRYBookEntity>)
}