package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.BookOrdersResponse
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookDetailModel

interface CryptoRemoteDataSourceInterface {
    suspend fun fetchBooks():List<BookModel>
    suspend fun fetchTickerInfo(book:String):BookDetailModel
    suspend fun fetchResumeBooks(book:String):List<BookOrdersResponse.BookResume>
}