package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.OrderBookResponse
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class OrderBookRepositoryImpl @Inject constructor  (private val remoteDataSource : NetworkDataSource) :
    OrderBookRepository {
    override suspend fun get_OrderBook(currency_name: String?): OrderBookResponse =
        remoteDataSource.getOrderBook(currency_name).body()!!

}
