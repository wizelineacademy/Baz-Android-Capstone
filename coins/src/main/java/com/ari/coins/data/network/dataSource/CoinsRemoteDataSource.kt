package com.ari.coins.data.network.dataSource

import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData

interface CoinsRemoteDataSource {
    suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>>
    suspend fun getTicker(book: String): ResultData<TickerData>
    suspend fun getOrderBook(book: String): ResultData<OrderBookData>
}