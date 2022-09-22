package com.example.myapplication.api

import com.example.myapplication.api.RetroFitRxClient.cliente
import com.example.myapplication.api.interfaces.ApiBitsoService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by: Juan Antonio Amado
 * date: 20,septiembre,2022
 */
object RetroFitRxClient {

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val cliente = OkHttpClient.Builder()
    .addInterceptor(interceptor)


    private val retrofitCatalog = Retrofit.Builder()
        .baseUrl("https://api.bitso.com/v3/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(cliente.build())
        .build()
        .create(ApiBitsoService::class.java)

    fun buildService2(): ApiBitsoService {
        return retrofitCatalog
    }
}