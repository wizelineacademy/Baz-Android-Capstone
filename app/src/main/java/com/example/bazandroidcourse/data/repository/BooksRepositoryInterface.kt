package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel

interface BooksRepositoryInterface {
    suspend fun getAllBooks():List<BookModel>
    suspend fun getBookInfo(id:String):BookDetailModel
    suspend fun getBookOrders(id:String):BookOrdersModel
}