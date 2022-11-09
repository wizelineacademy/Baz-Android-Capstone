package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookDetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.DetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderPayloadResponse

interface CryptoRemoteDataSourceInterface {
    suspend fun fetchAllBooks():List<BookResponse>
    suspend fun fetchBookDetail(book:String):DetailResponse
    suspend fun fetchBookOrders(book:String):OrderPayloadResponse
}