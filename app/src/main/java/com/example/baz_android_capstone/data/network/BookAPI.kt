package com.example.baz_android_capstone.data.network

import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.ticker.Ticker
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BookAPI {
    companion object {
        const val AVAILABLE_BOOK_URL = "https://api.bitso.com/v3/"
    }

    @GET("available_books")
    suspend fun getAllAvailableBooks(): Book

    @GET("ticker")
    suspend fun getTicker(@Query("book") book: String): Ticker

    @GET("order_book")
    suspend fun getOrderBook(@Query("book") book: String): OrderBook
}
