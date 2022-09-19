package com.ari.coins.data.network.repository

import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.AvailableBooks
import com.ari.coins.data.models.Result
import com.ari.coins.data.models.Ticker
import com.ari.coins.data.network.dataSource.CoinsRemoteDataSource
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource
) {

    suspend fun getAvailableBooks(): Result<List<AvailableBooks>> = coinsRemoteDataSource.getAvailableBooks()
    suspend fun getTicker(book: String): Result<Ticker> = coinsRemoteDataSource.getTicker(book)
    suspend fun getOrderBook(book: String): Result<OrderBook> = coinsRemoteDataSource.getOrderBook(book)

}