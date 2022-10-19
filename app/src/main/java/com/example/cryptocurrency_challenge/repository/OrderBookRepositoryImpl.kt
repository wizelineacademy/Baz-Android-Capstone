package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.PayloadOrderBook
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class OrderBookRepositoryImpl @Inject constructor  (private val remoteDataSource : NetworkDataSource) :
    OrderBookRepository {
    override suspend fun getOrderBook(currency_name: String): PayloadOrderBook {
        val result = remoteDataSource.getOrderBook(currency_name).body()
            return result?.payload ?: PayloadOrderBook()
    }
}
