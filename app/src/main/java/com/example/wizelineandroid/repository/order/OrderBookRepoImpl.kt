package com.example.wizelineandroid.repository.order

import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.data.remote.model.OrderBook
import javax.inject.Inject

class OrderBookRepoImpl @Inject constructor(private val dataSource: BooksDataSource) : OrderBookRepo {
    override suspend fun getOrderBooks(id: String): OrderBook = dataSource.getOrderBooks(id)
}
