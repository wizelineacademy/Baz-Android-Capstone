package com.example.cryptocurrency_challenge.data.network

import com.example.cryptocurrency_challenge.data.model.Available_books_response
import com.example.cryptocurrency_challenge.data.model.InfoTickerResponse
import com.example.cryptocurrency_challenge.data.model.OrderBookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
* https://bitso.com/api_info#available-books
* https://bitso.com/api_info#ticker
* https://bitso.com/api_info#order-book
*/

interface ApiBitsoService {

    @GET("https://api.bitso.com/v3/ticker/")
    suspend fun getInfoTicker(@Query("book") value:String) :
            Response<InfoTickerResponse>

    @GET("https://api.bitso.com/v3/available_books/")
    suspend fun getAllCurrencies() :
            Response<Available_books_response>

    @GET ("https://api.bitso.com/v3/order_book/")
    suspend fun getOrderBook(@Query("book") value:String): Response<OrderBookResponse>

}