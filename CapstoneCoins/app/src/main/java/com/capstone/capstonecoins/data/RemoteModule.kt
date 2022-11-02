package com.capstone.capstonecoins.data

import com.capstone.capstonecoins.data.utils.URL_BASE
import com.capstone.capstonecoins.domain.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    var retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)