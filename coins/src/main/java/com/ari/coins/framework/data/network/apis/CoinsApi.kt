package com.ari.coins.framework.data.network.apis

import com.ari.coins.data.models.AvailableBooks
import com.ari.coins.data.models.CryptoResponse
import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.Ticker
import com.ari.coins.framework.data.network.constants.Endpoints
import retrofit2.Response
import retrofit2.http.GET

interface CoinsApi {

    @GET(Endpoints.AVAILABLE_BOOKS)
    suspend fun getAvailableBooks(): Response<CryptoResponse<List<AvailableBooks>>>

    @GET(Endpoints.TICKER)
    suspend fun getTicker(): Response<CryptoResponse<Ticker>>

    @GET(Endpoints.ORDER_BOOK)
    suspend fun getOrderBook(): Response<CryptoResponse<OrderBook>>

}