package com.example.myapplication.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by: Juan Antonio Amado
 * date: 16,septiembre,2022
 */
class ApiRetro {

    companion object {
        private const val BASE_URL = "https://api.bitso.com/v3/"

        fun getRetorInstance(): Retrofit {

            val loggin = HttpLoggingInterceptor()
            loggin.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                client.addInterceptor(loggin)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}