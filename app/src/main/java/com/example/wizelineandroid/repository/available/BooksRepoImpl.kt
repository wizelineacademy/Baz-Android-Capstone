package com.example.wizelineandroid.repository.available

import com.example.wizelineandroid.data.model.Books
import com.example.wizelineandroid.data.remote.BooksDataSource

class BooksRepoImpl(private val dataSource: BooksDataSource): BooksRepo {
    override suspend fun getAvailableBooks(): Books = dataSource.getAvailablesbooks()
}