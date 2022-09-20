package com.ari.coins.data.network.dataSource

import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.AvailableBook
import com.ari.coins.data.models.Result
import com.ari.coins.data.models.Ticker

interface CoinsRemoteDataSource {
    suspend fun getAvailableBooks(): Result<List<AvailableBook>>
    suspend fun getTicker(book: String): Result<Ticker>
    suspend fun getOrderBook(book: String): Result<OrderBook>
}