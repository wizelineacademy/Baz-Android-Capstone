package com.example.wizelineandroid.repository.ticker

import com.example.wizelineandroid.data.remote.model.GetTickers

interface TickerRepo {
    suspend fun getTickerBooks(id:String): GetTickers
}