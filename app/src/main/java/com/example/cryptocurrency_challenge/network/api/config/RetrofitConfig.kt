package com.example.cryptocurrency_challenge.network.api.config

import com.example.cryptocurrency_challenge.network.api.ApiBitso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {


    val URL_BASE = "https://jsonplaceholder.typicode.com/"
    fun obtenerConfiguracionRetrofit():ApiBitso{

        val mRetrofit= Retrofit.Builder(
        )
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return mRetrofit.create(ApiBitso::class.java)
    }




}