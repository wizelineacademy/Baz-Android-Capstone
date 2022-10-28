package com.capstone.capstonecoins.data

import com.capstone.capstonecoins.data.utils.URL_BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteModule {

    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}