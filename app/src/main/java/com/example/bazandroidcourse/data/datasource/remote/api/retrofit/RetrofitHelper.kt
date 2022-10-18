package com.example.bazandroidcourse.data.datasource.remote.api.retrofit

import com.example.bazandroidcourse.data.datasource.remote.api.APIConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofitHelper():Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConstants.API_PRINCIPAL_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}