package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResume
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookDetailModel

interface CryptoRemoteDataSourceInterface {
    suspend fun fetchAllBooks():List<BookModel>
    suspend fun fetchBookDetail(book:String):BookDetailModel
    suspend fun fetchBookOrders(book:String):List<BookResume>
}