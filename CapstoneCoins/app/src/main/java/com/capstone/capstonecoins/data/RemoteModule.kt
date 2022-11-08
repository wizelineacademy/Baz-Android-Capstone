package com.capstone.capstonecoins.data

import com.capstone.capstonecoins.data.utils.URL_BASE
import com.capstone.capstonecoins.domain.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

lateinit var httpClientBuilder: OkHttpClient.Builder
lateinit var logging: HttpLoggingInterceptor

fun createInterceptor(): OkHttpClient {
    httpClientBuilder = OkHttpClient.Builder()
    logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClientBuilder.addInterceptor(logging)
    return httpClientBuilder.build()
}

var retrofit = retrofit2.Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .client(createInterceptor())
    .build().create(ApiService::class.java)