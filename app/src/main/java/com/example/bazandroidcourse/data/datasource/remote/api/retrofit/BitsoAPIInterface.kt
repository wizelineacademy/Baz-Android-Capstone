package com.example.bazandroidcourse.data.datasource.remote.api.retrofit

import com.example.bazandroidcourse.data.datasource.remote.api.response.BooksResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderBookResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.TickerInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApplicationAPIInterface {

    @GET("v3/available_books/")
    fun getBooks():BooksResponse

    @GET("v3/ticker/")
    fun getTicker(@Query("book") book: String):TickerInfoResponse

    @GET("v3/order_book/")
    fun getOrderBook(@Query("book") book: String):OrderBookResponse

}