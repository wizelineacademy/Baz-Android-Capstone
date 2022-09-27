package com.ari.coins.data.network.repository

import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.dataSource.CoinsRemoteDataSource
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource
) {

    suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>> = coinsRemoteDataSource.getAvailableBooks()
    suspend fun getTicker(book: String): ResultData<TickerData> = coinsRemoteDataSource.getTicker(book)
    suspend fun getOrderBook(book: String): ResultData<OrderBookData> = coinsRemoteDataSource.getOrderBook(book)

}