package com.course.criptomonedas.core

import com.course.criptomonedas.Constants
import com.course.criptomonedas.data.network.AvailableBooksService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_CRIPTOS)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val service: AvailableBooksService =
    RetrofitClient.retrofit.create(AvailableBooksService::class.java)

