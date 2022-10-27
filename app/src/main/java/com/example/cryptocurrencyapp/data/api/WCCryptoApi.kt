package com.example.cryptocurrencyapp.data.api

import com.example.cryptocurrencyapp.data.entity.response.WCCryptoTickerResponse
import com.example.cryptocurrencyapp.data.entity.response.WCCryptoAvailableResponse
import com.example.cryptocurrencyapp.data.entity.response.WCCryptoOrderResponse
import com.example.cryptocurrencyapp.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WCCryptoApi {

    @GET (WCCryptoEndPoints.AVAILABLE_BOOKS)
    suspend fun getExchangeBooks(): WCCryptoAvailableResponse

    @GET (WCCryptoEndPoints.TICKER_BOOK)
    suspend fun getTickerBook(
        @Query("book") book: String
    ): WCCryptoTickerResponse

    @GET (WCCryptoEndPoints.ORDER_SPECIFIED_BOOK)
    suspend fun getOrderBook(
        @Query("book") book: String
    ): WCCryptoOrderResponse
}