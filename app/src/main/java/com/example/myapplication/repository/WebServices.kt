package com.example.myapplication.repository

import com.example.myapplication.application.AppConstants
import com.example.myapplication.data.model.BookResponse
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("available_books/")
    suspend fun getAvailableBooks(@Query("api_key") apiKey: String): BookResponse
    suspend fun books(): Response<BookResponse>
}



object RetrofitClient{
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}