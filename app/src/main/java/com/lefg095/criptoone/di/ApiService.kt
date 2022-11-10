package com.lefg095.criptoone.di

import com.lefg095.criptoone.domain.*
import com.lefg095.criptoone.domain.response.BaseResponse
import com.lefg095.criptoone.domain.response.OrderResponse
import com.lefg095.criptoone.domain.response.TickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("available_books/")
    suspend fun getBooks(
    ): BaseResponse<Book>

    @GET("ticker/")
    suspend fun getTicker(
        @Query("book") nameBook: String
    ): TickerResponse

    @GET("order_book/")
    suspend fun getOrders(
        @Query("book") nameBook: String
    ): OrderResponse
}