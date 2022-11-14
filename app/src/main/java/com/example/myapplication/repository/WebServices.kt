package com.example.myapplication.repository

import com.example.myapplication.application.AppConstants
import com.example.myapplication.data.model.BookResponse
import com.example.myapplication.data.model.OrderBooksModel
import com.example.myapplication.data.model.TickerPayloadResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {


    @GET("ticker/")
    suspend fun getTicker(@Query("api_key") apiKey: String): TickerPayloadResponse

    @GET("order_book/book")
    suspend fun getOrderBook(@Query("btc_mxn") apiKey: String): OrderBooksModel


    @GET("available_books/")
    suspend fun getAvailableBook(@Query("api_key") apiKey: String): BookResponse
}


object RetrofitClient{
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}