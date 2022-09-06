package com.example.criptobitsoproyectwz.data.network

import com.example.criptobitsoproyectwz.data.model.BaseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceBitso {
    @GET
    suspend fun getCriptos(@Url url: String): Response<BaseResult>
}