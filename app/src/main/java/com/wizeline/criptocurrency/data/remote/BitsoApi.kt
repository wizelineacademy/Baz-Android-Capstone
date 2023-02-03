package com.wizeline.criptocurrency.data.remote.dto

import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.data.remote.dto.response.OrderBookResponse
import com.wizeline.criptocurrency.data.remote.dto.response.TickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoApi {

    @GET("available_books")
    suspend fun getAvaliableBooks(): AvailableBooksResponse

    @GET("ticker/")
    suspend fun getTicker(
        @Query("book") book: String,
    ): TickerResponse

    @GET("order_book/")
    suspend fun getOrderBook(
        @Query("book") book: String
    ): OrderBookResponse

    @GET("available_books")
    fun getAvailableBooksRxJava(): Single<AvailableBooksResponse>
}
