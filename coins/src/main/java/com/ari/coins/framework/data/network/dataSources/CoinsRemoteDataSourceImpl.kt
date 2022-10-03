package com.ari.coins.framework.data.network.dataSources

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import com.ari.coins.framework.data.network.execute
import javax.inject.Inject

/**
 * @author        Ari Valencia
 * @file          CoinsRemoteDataSourceImpl
 * @description   Implementation of CoinsRemoteDataSource, get info from Crypto API
 */

class CoinsRemoteDataSourceImpl @Inject constructor(
    private val coinsApi: CoinsApi
) : CoinsRemoteDataSource {

    override suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>> =
        execute { coinsApi.getAvailableBooks() }

    override suspend fun getTicker(book: String): ResultData<TickerData> =
        execute { coinsApi.getTicker(book) }

    override suspend fun getOrderBook(book: String): ResultData<OrderBookData> =
        execute { coinsApi.getOrderBook(book) }

}