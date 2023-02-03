package com.carteagal.baz_android.data.repository

import com.carteagal.baz_android.data.dataSources.CryptoRemoteDataSources
import com.carteagal.baz_android.data.model.orderBook.OrderBookResponse
import javax.inject.Inject

class OrderBooksRepository @Inject constructor(
    private val apiDataSources: CryptoRemoteDataSources
) {

    suspend fun getOrderBooks(book: String): List<OrderBookResponse>{
        return  apiDataSources.getOrderBooks(book)
    }
}