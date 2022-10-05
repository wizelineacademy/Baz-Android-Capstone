package com.ari.coins.data.repository

import com.ari.coins.data.local.CoinsLocalDataSource
import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.CoinsRemoteDataSource
import javax.inject.Inject

/**
 * @author Ari Valencia
 * @file CoinsRepository
 * @description Repository with CRUD for
 *                  - Available books
 *                  - Order books
 *                  - Tickers
 */

class CoinsRepository @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private val coinsLocalDataSource: CoinsLocalDataSource
) {

    suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>> =
        coinsRemoteDataSource.getAvailableBooks()

    suspend fun getTicker(book: String): ResultData<TickerData> =
        coinsRemoteDataSource.getTicker(book)

    suspend fun getOrderBook(book: String): ResultData<OrderBookData> =
        coinsRemoteDataSource.getOrderBook(book)

    suspend fun getAvailableBooksFromDB(): List<AvailableBookData> =
        coinsLocalDataSource.getAvailableBooksFromDB()

    suspend fun getTickerFromDB(book: String): TickerData? =
        coinsLocalDataSource.getTickerFromDB(book)

    suspend fun getOrderBookFromDB(book: String): OrderBookData? =
        coinsLocalDataSource.getOrderBookFromDB(book)

    suspend fun deleteTickerFromDB(book: String) = coinsLocalDataSource.deleteTickerFromDB(book)

    suspend fun deleteOrderBookFromDB(book: String) =
        coinsLocalDataSource.deleteOrderBookFromDB(book)

    suspend fun insertAvailableBooksToDB(availableBooks: List<AvailableBookData>) =
        coinsLocalDataSource.insertAvailableBooksToDB(availableBooks)

    suspend fun insertTickerToDB(ticker: TickerData) = coinsLocalDataSource.insertTickerToDB(ticker)

    suspend fun insertOrderBookToDB(book: String, orderBook: OrderBookData) =
        coinsLocalDataSource.insertOrderBookToDB(book, orderBook)

    suspend fun clearAvailableBookTableFormDB() =
        coinsLocalDataSource.clearAvailableBookTableFormDB()
}
