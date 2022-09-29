package com.proyect.cursowizline.api

import com.proyect.cursowizline.model.CryptoListResponse
import com.proyect.cursowizline.model.CryptoOrderListResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(BOOKS)
    suspend fun getAllCrypto(): CryptoListResponse


    @GET(ORDER_BOOK)
    suspend fun getOrderCrypto(@Query("book") book: String): CryptoOrderListResponse
}

