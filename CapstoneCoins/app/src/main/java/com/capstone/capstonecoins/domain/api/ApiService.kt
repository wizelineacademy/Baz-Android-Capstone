package com.capstone.capstonecoins.domain.api

import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.data.models.orderbook.orderBook
import com.capstone.capstonecoins.data.models.ticker.Ticker
import retrofit2.http.GET

interface ApiService {

    @GET("available_books/")
    suspend fun getAvailableBooks(): BooksDto

    @GET("order_book/")
    suspend fun getOrderBooks(): orderBook

    @GET("ticker/")
    suspend fun getTicker(): Ticker

}