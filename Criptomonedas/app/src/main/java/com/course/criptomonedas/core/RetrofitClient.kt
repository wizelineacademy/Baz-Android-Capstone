package com.course.criptomonedas.core

import com.course.criptomonedas.Constants
import com.course.criptomonedas.data.network.AvailableBooksService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client =
        OkHttpClient.Builder().addInterceptor(interceptor).addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .header("User-Agent", "User-Agent")
                    .build()
            )
        }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_CRIPTOS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()
}

val service: AvailableBooksService =
    RetrofitClient.retrofit.create(AvailableBooksService::class.java)
