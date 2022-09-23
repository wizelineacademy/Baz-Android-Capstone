package com.proyect.cursowizline.api

import com.proyect.cursowizline.model.CryptoListResponse
import com.proyect.cursowizline.model.CryptoOrderListResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService {
    @GET(BOOKS)
    suspend fun getAllCrypto(): CryptoListResponse

    @GET(ORDER_BOOK)
    suspend fun getOrderCrypto(@Query("book") book: String): CryptoOrderListResponse
}

object CryptoApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}