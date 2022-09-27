package com.ari.coins.framework.data.network.dataSources

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.dataSource.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import com.ari.coins.framework.data.network.toResult
import javax.inject.Inject

class CoinsRemoteDataSourceImpl @Inject constructor(
    private val coinsApi: CoinsApi
) : CoinsRemoteDataSource {

    override suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>> =
        coinsApi.getAvailableBooks().toResult()

    override suspend fun getTicker(book: String): ResultData<TickerData> =
        coinsApi.getTicker(book).toResult()

    override suspend fun getOrderBook(book: String): ResultData<OrderBookData> =
        coinsApi.getOrderBook(book).toResult()

}