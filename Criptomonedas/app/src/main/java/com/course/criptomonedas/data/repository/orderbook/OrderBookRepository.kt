package com.course.criptomonedas.data.repository.orderbook

import com.course.criptomonedas.data.models.OrderBook

interface OrderBookRepository {
    suspend fun getOrderBooks(book: String): OrderBook
}