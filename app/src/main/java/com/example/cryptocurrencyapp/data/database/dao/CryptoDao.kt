package com.example.cryptocurrencyapp.data.database.dao


import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity
import androidx.room.*

@Dao
interface CryptoDao {

    @Query("SELECT * FROM available_table")
    fun getAllAvailableBookDB(): List<AvailableBookEntity>

    @Insert
    fun insertAvailableBooDB (book: List<AvailableBookEntity>)

    @Update
    fun updateAvailableBookDB(bookList: List<AvailableBookEntity>)

    @Query("DELETE FROM available_table")
    fun deleteAllAvailableBookDB()

}