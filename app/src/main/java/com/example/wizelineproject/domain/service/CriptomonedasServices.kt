package com.example.wizelineproject.domain.service

import com.example.wizelineproject.domain.model.Book
import com.example.wizelineproject.domain.model.OrderBook
import com.example.wizelineproject.domain.model.Ticker
import com.example.wizelineproject.domain.response.GenericArrayResponse
import com.example.wizelineproject.domain.response.GenericObjectResponse
import retrofit2.http.GET

interface CriptomonedasServices {

    @GET("available_books")
    suspend fun getBooks(): GenericArrayResponse<Book>?

    @GET("ticker")
    suspend fun getTickers(): GenericArrayResponse<Ticker>?

    @GET("order_book")
    suspend fun getBookOrders(): GenericObjectResponse<OrderBook>?

}