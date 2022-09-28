package com.example.cryptocurrency_challenge.data.network.config

import com.example.cryptocurrency_challenge.data.network.ApiBitsoService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitConfig {

    val URL_BASE = "https://jsonplaceholder.typicode.com/"
    fun obtenerConfiguracionRetrofit():ApiBitsoService{

        val mRetrofit= Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return mRetrofit.create(ApiBitsoService::class.java)
    }

}


object RetrofitClient {

    private const val BASE_URL = "https://rickandmortyapi.com/"

    private val okHttpClient = OkHttpClient.Builder()
        //.addInterceptor(HttpLoggingInterceptor().also { it.setLevel(HttpLoggingInterceptor.Level.BODY) })
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        //.addConverterFactory(MoshiConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())     // Para RXjava
        .build()

}