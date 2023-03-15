package com.example.wizelineandroid.repository.order

import com.example.wizelineandroid.data.remote.model.OrderBook

interface OrderBookRepo {
    suspend fun getOrderBooks(id: String): OrderBook
}
