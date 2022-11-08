package com.wizeline.criptocurrency.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.wizeline.criptocurrency.data.database.entities.AsksEntity
import com.wizeline.criptocurrency.data.database.entities.AvailableBookEntity
import com.wizeline.criptocurrency.data.database.entities.BidsEntity
import com.wizeline.criptocurrency.data.database.entities.TickerEntity

interface CryptoCurrencyDao {

    @Query("SELECT * FROM available_book_table")
    fun getAllAvailableBooksFromDatabase(): List<AvailableBookEntity>

    @Insert
    fun insertAvailableOrderBookToDatabase(bookList: List<AvailableBookEntity>)

    @Update
    fun updateAvailableOrderBookDatabase(bookList: List<AvailableBookEntity>)

    @Query("DELETE FROM available_book_table")
    fun deleteAllAvailableOrderBookDatabase()


    @Query("SELECT * FROM ticker_table WHERE book LIKE :book")
    fun getTickerFromDatabase(book: String): TickerEntity

    @Insert
    fun insertTickerToDatabase(ticker: TickerEntity)

    @Update
    fun updateTickerDatabase(ticker: TickerEntity)

    @Query("DELETE FROM ticker_table WHERE book LIKE :book")
    fun deleteTickerDatabase(book: String)

    @Query("SELECT * FROM bids_table WHERE book LIKE :book")
    fun getAllOrderBookBidsFromDatabase(book: String): List<BidsEntity>

    @Query("SELECT * FROM asks_table WHERE book LIKE :book")
    fun getAllOrderBookAsksFromDatabase(book: String): List<AsksEntity>



}