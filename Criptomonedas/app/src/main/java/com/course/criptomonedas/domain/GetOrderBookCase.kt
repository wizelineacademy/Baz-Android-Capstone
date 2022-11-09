package com.course.criptomonedas.domain

import com.course.criptomonedas.data.models.OrderBook
import com.course.criptomonedas.data.repository.orderbook.OrderBookRepository

class GetOrderBookCase(private val repository: OrderBookRepository) {
    suspend operator fun invoke(book: String): OrderBook = repository.getOrderBooks(book)
}