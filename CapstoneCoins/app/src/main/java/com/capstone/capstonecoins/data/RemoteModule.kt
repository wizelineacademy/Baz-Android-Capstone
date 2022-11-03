package com.capstone.capstonecoins.data

import com.capstone.capstonecoins.data.utils.URL_BASE
import com.capstone.capstonecoins.domain.api.ApiService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit as anuma

var retrofit = anuma.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)