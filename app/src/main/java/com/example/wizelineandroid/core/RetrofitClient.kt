package com.example.wizelineandroid.core

import com.example.wizelineandroid.repository.WebService
import com.example.wizelineandroid.utils.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//peticion a datasource, covertir json de api a movie
object RetrofitClient{

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }

    private const val userAgent = ""

    private val client =
        OkHttpClient.Builder().addInterceptor(interceptor).addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .header("User-Agent", userAgent)
                    .build()
            )
        }

    val webService: WebService by lazy {
        Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client.build())
            .build().create(WebService::class.java)
    }
}