package com.wizeline.criptocurrency.common.adapters

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wizeline.criptocurrency.data.remote.dto.BitsoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.bitso.com/v3/"

    val gson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        .setLenient()
        .create()

    private fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun repository(): BitsoApi = getRetrofit().create(BitsoApi::class.java)

}