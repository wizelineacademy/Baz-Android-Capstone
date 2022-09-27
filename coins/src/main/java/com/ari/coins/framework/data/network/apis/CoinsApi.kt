package com.ari.coins.framework.data.network.apis

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.CryptoResponseData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.TickerData
import com.ari.coins.framework.data.network.constants.Endpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsApi {

    @GET(Endpoints.AVAILABLE_BOOKS)
    suspend fun getAvailableBooks(): Response<CryptoResponseData<List<AvailableBookData>>>

    @GET(Endpoints.TICKER)
    suspend fun getTicker(@Query("book") book: String): Response<CryptoResponseData<TickerData>>

    @GET(Endpoints.ORDER_BOOK)
    suspend fun getOrderBook(@Query("book") book: String): Response<CryptoResponseData<OrderBookData>>

}