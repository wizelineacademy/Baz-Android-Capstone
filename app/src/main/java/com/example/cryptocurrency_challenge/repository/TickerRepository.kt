package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.InfoTickerResponse

interface TickerRepository {
    suspend fun get_Ticker(currency_name: String?) : InfoTickerResponse
}
