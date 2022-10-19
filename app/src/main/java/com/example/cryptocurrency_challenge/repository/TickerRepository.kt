package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.PayloadTicker

interface TickerRepository {
    suspend fun getTicker(currency_name: String?) : PayloadTicker
}
