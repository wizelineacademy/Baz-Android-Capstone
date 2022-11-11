package com.course.criptomonedas.data.repository.orderbook

import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSource
import com.course.criptomonedas.data.models.OrderBook

class OrderBookRepositoryImpl(
    private val dataSource: RemoteDataSource
) : OrderBookRepository {
    override suspend fun getOrderBooks(book: String): OrderBook = dataSource.getOrderBook(book)
}