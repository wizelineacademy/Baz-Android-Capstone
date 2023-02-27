package com.example.wizelineandroid.repository

import com.example.wizelineandroid.data.remote.model.Books
import com.example.wizelineandroid.data.remote.model.GetTickers
import com.example.wizelineandroid.data.remote.model.OrderBook
import com.example.wizelineandroid.utils.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {

    @GET("available_books/")
    suspend fun getAvailableBooks(): Books

    @GET("ticker/")
    suspend fun getTickerBooks(@Query("book") id: String = ""): GetTickers

    @GET("order_book/")
    suspend fun getOrderBooks(@Query("book") id: String = ""): OrderBook

}
