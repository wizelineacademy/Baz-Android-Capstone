package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel

interface CryptoRemoteDataSourceInterface {
    suspend fun fetchAllBooks():List<BookModel>
    suspend fun fetchBookDetail(book:String):BookDetailModel
    suspend fun fetchBookOrders(book:String):BookOrdersModel
}