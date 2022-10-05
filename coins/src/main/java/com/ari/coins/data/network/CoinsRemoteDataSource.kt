package com.ari.coins.data.network

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData

/**
 * @author Ari Valencia
 * @file CoinsRemoteDataSource
 * @description Contract for any CoinsRemoteDataSourceImpl
 */

interface CoinsRemoteDataSource {
    suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>>
    suspend fun getTicker(book: String): ResultData<TickerData>
    suspend fun getOrderBook(book: String): ResultData<OrderBookData>
}
