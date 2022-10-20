package com.example.bazandroidcourse.data.datasource.local

import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookDetailModel

interface CryptoLocalDataSourceInterface {
    //Books
    suspend fun saveAllBooks(books:List<BookModel>)
    suspend fun getAllBooks():List<BookModel>
    suspend fun deleteAllBooks()
    //Book Details
    suspend fun saveBookDetail(bookDetail:BookDetailModel)
    suspend fun getBookDetail(id:String):BookDetailModel
    suspend fun deleteAllBookDetails()
    //Book Orders
    suspend fun saveAllBookOrder()
    suspend fun getBookOrder()
    suspend fun deleteAllBookOrders()
}