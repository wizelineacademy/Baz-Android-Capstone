package com.ari.coins.framework.data.network.dataSources

import com.ari.coins.data.models.AvailableBook
import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.Result
import com.ari.coins.data.models.Ticker
import com.ari.coins.data.network.dataSource.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import com.ari.coins.framework.data.network.toResult
import javax.inject.Inject

class CoinsRemoteDataSourceImpl @Inject constructor(
    private val coinsApi: CoinsApi
) : CoinsRemoteDataSource {

    override suspend fun getAvailableBooks(): Result<List<AvailableBook>> =
        coinsApi.getAvailableBooks().toResult()

    override suspend fun getTicker(book: String): Result<Ticker> =
        coinsApi.getTicker(book).toResult()

    override suspend fun getOrderBook(book: String): Result<OrderBook> =
        coinsApi.getOrderBook(book).toResult()

}