package com.example.wizelineandroid.repository.ticker

import com.example.wizelineandroid.data.model.GetTickers

interface TickerRepo {
    suspend fun getTickerBooks(id:String): GetTickers
}