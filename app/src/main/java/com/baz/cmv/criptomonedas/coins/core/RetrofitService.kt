package com.baz.cmv.criptomonedas.coins.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun getRetrofitInstance(): Retrofit{
        val retrofitClient: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.bitso.com/")
            .build()

        return retrofitClient
    }
}

