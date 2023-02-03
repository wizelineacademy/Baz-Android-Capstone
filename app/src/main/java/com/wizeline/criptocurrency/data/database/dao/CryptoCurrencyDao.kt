package com.wizeline.criptocurrency.data.database.dao

import androidx.room.*
import com.wizeline.criptocurrency.data.database.entities.*
import com.wizeline.criptocurrency.domain.model.OrderBook

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM available_book_table")
    fun getAllAvailableBooksFromDatabase(): List<AvailableBookEntity>

    @Insert
    fun insertAvailableOrderBookToDatabase(bookList: List<AvailableBookEntity>)

    @Update
    fun updateAvailableOrderBookDatabase(bookList: List<AvailableBookEntity>)

    @Query("DELETE FROM available_book_table")
    fun deleteAllAvailableBooksDatabase()

    @Query("SELECT * FROM ticker_table WHERE book LIKE :book")
    fun getTickerFromDatabase(book: String): TickerEntity

    @Insert
    fun insertTickerToDatabase(ticker: TickerEntity)

    @Update
    fun updateTickerDatabase(ticker: TickerEntity)

    @Query("DELETE FROM ticker_table WHERE book LIKE :book")
    fun deleteTickerDatabase(book: String)

    // OrderBook Bids
    @Query("SELECT * FROM bids_table WHERE book LIKE :book")
    fun getAllOrderBookBidsFromDatabase(book: String): List<BidsEntity>

    @Insert
    fun insertOrderBookBidsToDatabase(bidsEntity: List<BidsEntity>)

    @Query("DELETE FROM bids_table WHERE book LIKE :book")
    fun deleteAllOrderBookBidsDatabase(book: String)

    // OrderBook Asks
    @Query("SELECT * FROM asks_table WHERE book LIKE :book")
    fun getAllOrderBookAsksFromDatabase(book: String): List<AsksEntity>

    @Insert
    fun insertOrderBookAsksToDatabase(asksEntityList: List<AsksEntity>)

    @Query("DELETE FROM asks_table WHERE book LIKE :book")
    fun deleteAllOrderBookAsksDatabase(book: String)

    // Order Book Open Orders
    @Transaction
    fun getOrderBookFromDatabase(book: String): OrderBook {
        val bids = getAllOrderBookBidsFromDatabase(book)
        val asks = getAllOrderBookAsksFromDatabase(book)
        return OrderBook(
            book = book,
            bids = bids.map { it.toBidsOpenOrderFromEntity() },
            asks = asks.map { it.toAsksOpenOrderFromEntity() }
        )
    }

    @Transaction
    fun insertOrderBookOpenOrdersFromDatabase(
        bidsEntityList: List<BidsEntity>,
        asksEntityList: List<AsksEntity>
    ) {
        insertOrderBookBidsToDatabase(bidsEntityList)
        insertOrderBookAsksToDatabase(asksEntityList)
    }

    @Transaction
    fun deleteOrderBookOpenOrdersFromDatabase(book: String) {
        deleteAllOrderBookBidsDatabase(book)
        deleteAllOrderBookAsksDatabase(book)
    }
}
