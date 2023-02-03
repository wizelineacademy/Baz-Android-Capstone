package com.vero.cursowizelinecriptomonedas.api

import com.vero.cursowizelinecriptomonedas.api.response.CryptoListApiResponse
import com.vero.cursowizelinecriptomonedas.api.response.CryptoOrderListApiResponse
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
    suspend fun getAllCrypto(): CryptoListApiResponse

    @GET(ORDER_BOOK)
    suspend fun getOrderCrypto(@Query("book") book: String): CryptoOrderListApiResponse
}

//Use Service
object CryptoApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}