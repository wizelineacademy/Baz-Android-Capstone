package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.OrderBookResponse

interface OrderBookRepository {
    suspend fun get_OrderBook(currency_name: String?) : OrderBookResponse
}
