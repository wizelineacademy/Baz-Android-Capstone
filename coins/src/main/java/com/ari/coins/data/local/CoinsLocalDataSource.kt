package com.ari.coins.data.local

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.TickerData

/**
 * @author Ari Valencia
 * @file CoinsLocalDataSource
 * @description Contract for any CoinsLocalDataSourceImpl
 */

interface CoinsLocalDataSource {

    suspend fun getAvailableBooksFromDB(): List<AvailableBookData>
    suspend fun getTickerFromDB(book: String): TickerData?
    suspend fun getOrderBookFromDB(book: String): OrderBookData?

    suspend fun deleteTickerFromDB(book: String)
    suspend fun deleteOrderBookFromDB(book: String)

    suspend fun insertAvailableBooksToDB(availableBooks: List<AvailableBookData>)
    suspend fun insertTickerToDB(ticker: TickerData)
    suspend fun insertOrderBookToDB(book: String, orderBook: OrderBookData)

    suspend fun clearAvailableBookTableFormDB()
}
