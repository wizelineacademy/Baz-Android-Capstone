package com.example.cryptocurrencyapp.data.database.dao


import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity
import androidx.room.*

@Dao
interface CryptoDao {

    @Query("SELECT * FROM available_table")
    suspend fun getAllAvailableBookDB(): List<AvailableBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvailableBooDB (book: List<AvailableBookEntity>)

    @Update
   suspend fun updateAvailableBookDB(bookList: List<AvailableBookEntity>)

    @Query("DELETE FROM available_table")
   suspend fun deleteAllAvailableBookDB()

}