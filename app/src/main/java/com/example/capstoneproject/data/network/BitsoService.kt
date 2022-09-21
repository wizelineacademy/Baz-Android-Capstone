package com.example.capstoneproject.data.network

import com.example.capstoneproject.data.model.availableBooks.AvailableBooksResponse
import com.example.capstoneproject.data.model.orderBook.OrderBookResponse
import com.example.capstoneproject.data.model.ticker.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoService {

    @GET("available_books/")
    suspend fun getAllAvailableBooks(): Response<AvailableBooksResponse>

    @GET("ticker/")
    suspend fun getTicker(@Query("book") book:String): Response<TickerResponse>

    @GET("order_book/")
    suspend fun getOrderBook(@Query("book") book:String): Response<OrderBookResponse>

}