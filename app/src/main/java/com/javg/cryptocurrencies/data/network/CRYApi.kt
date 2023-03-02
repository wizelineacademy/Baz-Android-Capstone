package com.javg.cryptocurrencies.data.network

import com.javg.cryptocurrencies.data.model.CRYBaseResponse
import com.javg.cryptocurrencies.data.model.CRYBookResponse
import com.javg.cryptocurrencies.data.model.CRYOrderBook
import com.javg.cryptocurrencies.data.model.CRYTicker
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface CRYApi {

    companion object {
        const val BASE_URL = "https://api.bitso.com/"
        const val END_POINT_AVAILABLE_BOOKS = "v3/available_books"
        const val END_POINT_ORDER_BOOK = "v3/order_book"
        const val END_POINT_TICKER = "v3/ticker"
    }

    @GET(END_POINT_AVAILABLE_BOOKS)
    suspend fun getListAvailableBooks(): CRYBaseResponse<List<CRYBookResponse>>

    @GET(END_POINT_AVAILABLE_BOOKS)
    fun getListAvailableBooksRX(): Observable<CRYBaseResponse<List<CRYBookResponse>>>

    @GET(END_POINT_TICKER)
    suspend fun getTicker(@Query("book") book: String): CRYBaseResponse<CRYTicker>

    @GET(END_POINT_ORDER_BOOK)
    suspend fun getOrderBook(@Query("book") book: String): CRYBaseResponse<CRYOrderBook>
}
