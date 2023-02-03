package com.carteagal.baz_android.data.dataSources

import com.carteagal.baz_android.data.model.availableBook.AvailableBookResponse
import com.carteagal.baz_android.data.model.base.BaseServiceResponse
import com.carteagal.baz_android.data.model.orderBook.OrderBookResponse
import com.carteagal.baz_android.data.model.tickerResponse.TickerResponse
import com.carteagal.baz_android.data.network.Resources

interface CryptoRemoteDataSources {
    suspend fun getAvailableBooks():  List<AvailableBookResponse>
    suspend fun getOrderBooks(book: String): List<OrderBookResponse>
    suspend fun getTicker(book: String): TickerResponse
}