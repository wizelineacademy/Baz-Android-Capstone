package com.example.cryptocurrency_challenge.data.network

import com.example.cryptocurrency_challenge.data.model.AvailableBooksResponse
import com.example.cryptocurrency_challenge.data.model.InfoTickerResponse
import com.example.cryptocurrency_challenge.data.model.OrderBookResponse
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getAvailableBooksRX()                : Response<AvailableBooksResponse>
    suspend fun getTicker(currency_name: String?)    : Response<InfoTickerResponse>
    suspend fun getOrderBook(currency_name: String) : Response<OrderBookResponse>
}