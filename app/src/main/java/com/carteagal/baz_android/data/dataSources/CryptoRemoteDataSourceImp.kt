package com.carteagal.baz_android.data.dataSources

import android.util.Log
import com.carteagal.baz_android.data.model.availableBook.AvailableBookResponse
import com.carteagal.baz_android.data.model.base.BaseServiceResponse
import com.carteagal.baz_android.data.model.orderBook.OrderBookResponse
import com.carteagal.baz_android.data.network.BaseApiResponse
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.network.safeApiCall
import com.carteagal.baz_android.data.service.CryptoApiClient
import javax.inject.Inject

class CryptoRemoteDataSourceImp @Inject constructor(
    private val apiClient: CryptoApiClient
) : CryptoRemoteDataSources {

    override suspend fun getAvailableBooks():  List<AvailableBookResponse> =
        apiClient.getAvailableBooks().result ?: listOf()

    override suspend fun getOrderBooks(book: String): List<OrderBookResponse> =
        apiClient.getOrderBooks(book).result ?: listOf()

     override suspend fun getTicker(book: String) =
        apiClient.getTicker(book)
}