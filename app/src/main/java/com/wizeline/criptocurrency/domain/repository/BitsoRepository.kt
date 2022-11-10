package com.wizeline.criptocurrency.domain.repository

import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.data.remote.dto.response.OrderBookResponse
import com.wizeline.criptocurrency.data.remote.dto.response.TickerResponse
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.model.OrderBook
import com.wizeline.criptocurrency.domain.model.Ticker
import retrofit2.Response

interface BitsoRepository {
     suspend fun getAvailableBooks(): List<AvailableBook>
     suspend fun getTicker(book: String): Ticker
     suspend fun getOrderBook(book: String): OrderBook
}