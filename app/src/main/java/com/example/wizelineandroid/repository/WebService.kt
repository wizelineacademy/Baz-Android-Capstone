package com.example.wizelineandroid.repository

import com.example.wizelineandroid.data.model.Books
import com.example.wizelineandroid.data.model.GetTickers
import com.example.wizelineandroid.data.model.OrderBook
import com.example.wizelineandroid.utils.AppConstants
import com.google.gson.GsonBuilder
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

//peticion a datasource, covertir json de api a movie
object RetrofitClient{
    val webservice: WebService by lazy {
        Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}
