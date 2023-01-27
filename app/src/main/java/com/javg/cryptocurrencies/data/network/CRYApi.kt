package com.javg.cryptocurrencies.data.network

import com.javg.cryptocurrencies.config.CRYConstantEndPoint
import com.javg.cryptocurrencies.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface CRYApi {
    @GET(CRYConstantEndPoint.END_POINT_AVAILABLE_BOOKS)
    suspend fun getListAvailableBooks(): CRYBaseResponse<List<CRYBookResponse>>

    @GET(CRYConstantEndPoint.END_POINT_TICKER)
    suspend fun getTicker(@Query("book") book: String): CRYBaseResponse<CRYTicker>

    @GET(CRYConstantEndPoint.END_POINT_ORDER_BOOK)
    suspend fun getOrderBook(@Query("book") book: String): CRYBaseResponse<CRYOrderBook>
}