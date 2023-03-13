package com.example.wizelineandroid.repository.available

import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.data.remote.model.Books
import javax.inject.Inject

class BooksRepoImpl @Inject constructor(private val dataSource: BooksDataSource) : BooksRepo {
    override suspend fun getAvailableBooks(): Books = dataSource.getAvailablesbooks()
}
