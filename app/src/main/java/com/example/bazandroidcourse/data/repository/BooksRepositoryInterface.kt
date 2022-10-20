package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookDetailModel

interface BooksRepositoryInterface {
    suspend fun getAllBooks():List<BookModel>
    suspend fun getBookInfo(id:String):BookDetailModel
}