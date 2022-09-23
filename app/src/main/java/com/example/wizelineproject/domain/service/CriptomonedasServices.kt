package com.example.wizelineproject.domain.service

import com.example.wizelineproject.domain.model.Book
import com.example.wizelineproject.domain.model.OrderBook
import com.example.wizelineproject.domain.model.Ticker
import com.example.wizelineproject.domain.response.GenericArrayResponse
import com.example.wizelineproject.domain.response.GenericObjectResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CriptomonedasServices {

    @GET("available_books")
    suspend fun getBooks(): GenericArrayResponse<Book>?

    @GET("ticker")
    suspend fun getTickers(@Query("book")book:String): GenericObjectResponse<Ticker>?

    @GET("order_book")
    suspend fun getBookOrders(@Query("book")book:String): GenericObjectResponse<OrderBook>?

}