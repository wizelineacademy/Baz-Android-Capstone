package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.PayloadOrderBook

interface OrderBookRepository {
    suspend fun getOrderBook(currency_name: String) : PayloadOrderBook
}
