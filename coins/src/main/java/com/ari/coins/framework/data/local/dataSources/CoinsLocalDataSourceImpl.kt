package com.ari.coins.framework.data.local.dataSources

import com.ari.coins.data.local.CoinsLocalDataSource
import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.TickerData
import com.ari.coins.framework.data.local.dao.AvailableBookDao
import com.ari.coins.framework.data.local.dao.OrderBookDao
import com.ari.coins.framework.data.local.dao.TickerDao
import com.ari.coins.framework.data.local.entities.AvailableBookEntity
import com.ari.coins.framework.data.local.entities.toData
import com.ari.coins.framework.data.local.entities.toEntity
import javax.inject.Inject

/**
 * @author Ari Valencia
 * @file CoinsLocalDataSourceImpl
 * @description Implementation for CoinsLocalDataSource for
 *                save, update and read local data using Room
 */

class CoinsLocalDataSourceImpl @Inject constructor(
    private val availableBookDao: AvailableBookDao,
    private val orderBookDao: OrderBookDao,
    private val tickerDao: TickerDao
) : CoinsLocalDataSource {

    override suspend fun getAvailableBooksFromDB(): List<AvailableBookData> =
        availableBookDao.getAllAvailableBooks().map(AvailableBookEntity::toData)

    override suspend fun getTickerFromDB(book: String): TickerData? =
        tickerDao.getTicker(book)?.toData()

    override suspend fun getOrderBookFromDB(book: String): OrderBookData? =
        orderBookDao.getOrderBook(book)?.toData()

    override suspend fun deleteTickerFromDB(book: String) = tickerDao.deleteTicker(book)

    override suspend fun deleteOrderBookFromDB(book: String) = orderBookDao.deleteOrderBook(book)

    override suspend fun insertAvailableBooksToDB(availableBooks: List<AvailableBookData>) =
        availableBookDao.insertAvailableBooks(availableBooks.map(AvailableBookData::toEntity))

    override suspend fun insertTickerToDB(ticker: TickerData) =
        tickerDao.insertTicker(ticker.toEntity())

    override suspend fun insertOrderBookToDB(book: String, orderBook: OrderBookData) =
        orderBookDao.insertOrderBook(orderBook.toEntity(book))

    override suspend fun clearAvailableBookTableFormDB() = availableBookDao.clearTable()
}
