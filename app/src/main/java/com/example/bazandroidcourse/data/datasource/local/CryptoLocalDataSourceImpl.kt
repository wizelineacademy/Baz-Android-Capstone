package com.example.bazandroidcourse.data.datasource.local

import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel

class CryptoLocalDataSourceImpl():CryptoLocalDataSourceInterface{
    override suspend fun saveAllBooks(books: List<BookModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllBooks(): List<BookModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllBooks() {
        TODO("Not yet implemented")
    }

    override suspend fun saveBookDetail(bookDetail: BookDetailModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetail(id: String): BookDetailModel {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllBookDetails() {
        TODO("Not yet implemented")
    }

    override suspend fun saveAllBookOrder() {
        TODO("Not yet implemented")
    }

    override suspend fun getBookOrder() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllBookOrders() {
        TODO("Not yet implemented")
    }
}