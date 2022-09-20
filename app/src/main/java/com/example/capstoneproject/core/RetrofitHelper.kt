package com.example.capstoneproject.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.bitso.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}