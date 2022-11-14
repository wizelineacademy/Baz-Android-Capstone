package com.example.myapplication.repository

import com.example.myapplication.data.model.BookResponse
import com.example.myapplication.data.model.OrderBooksModel
import com.example.myapplication.data.model.TickerPayloadResponse

interface BitsoRepository {

    suspend fun getTicker(): TickerPayloadResponse
    suspend fun getOrderBook(): OrderBooksModel
    suspend fun getAvailableBook(): BookResponse


}