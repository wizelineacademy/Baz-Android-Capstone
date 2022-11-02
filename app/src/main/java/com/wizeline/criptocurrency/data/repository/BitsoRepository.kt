package com.wizeline.criptocurrency.data.repository

import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.data.remote.dto.response.OrderBookResponse
import com.wizeline.criptocurrency.data.remote.dto.response.TickerResponse
import com.wizeline.criptocurrency.domain.model.AvailableBook
import retrofit2.Response

interface BitsoRepository {
    suspend fun getAvailableBooks(): Response<AvailableBooksResponse>
    suspend fun getTicker(book: String): Response<TickerResponse>
    suspend fun getOrderBook(book: String): Response<OrderBookResponse>
}