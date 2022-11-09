package com.course.criptomonedas.data.network

import com.course.criptomonedas.data.models.AvailableBooks
import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.models.OrderBook
import retrofit2.http.GET
import retrofit2.http.Query

interface AvailableBooksService {

    @GET("available_books/")
    suspend fun getBooks(): AvailableBooks

    @GET("ticker/")
    suspend fun getDetailBook(@Query("book") id: String): ModelDetails

    @GET("order_book/")
    suspend fun getBooksById(@Query("book") book: String): OrderBook
}