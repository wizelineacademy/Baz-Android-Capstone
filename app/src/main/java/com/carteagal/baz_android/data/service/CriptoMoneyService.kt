package com.carteagal.baz_android.data.service

import com.carteagal.baz_android.data.model.availableBook.AvailableBookResponse
import com.carteagal.baz_android.data.model.base.BaseResponseService
import com.carteagal.baz_android.data.model.orderBook.OrderBookResonse
import com.carteagal.baz_android.data.model.tickerResponse.TrickerResponse
import com.carteagal.baz_android.utils.Constants.PATH_AVAILABLE_BOOKS
import com.carteagal.baz_android.utils.Constants.PATH_ORDER_BOOK
import com.carteagal.baz_android.utils.Constants.PATH_TICKER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CriptoMoneyService {

    @GET(PATH_AVAILABLE_BOOKS)
    suspend fun getAvailableBooks(): Response<BaseResponseService<List<AvailableBookResponse>>>

    @GET(PATH_ORDER_BOOK)
    suspend fun getOrderBooks(
        @Query("book") book: String
    ): Response<BaseResponseService<List<OrderBookResonse>>>

    @GET(PATH_TICKER)
    suspend fun getTicker(
        @Query("book") book: String
    ): Response<BaseResponseService<TrickerResponse>>


}