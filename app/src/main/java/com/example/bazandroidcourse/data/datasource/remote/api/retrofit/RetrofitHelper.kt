package com.example.bazandroidcourse.data.datasource.remote.api.retrofit

import com.example.bazandroidcourse.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitInstance = Retrofit.Builder()
    .baseUrl(BuildConfig.API_PRINCIPAL_PATH)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiInstance = retrofitInstance.create(ApplicationAPIInterface::class.java)