package com.example.wizelineandroid.repository.available

import com.example.wizelineandroid.data.remote.model.Books
import com.example.wizelineandroid.data.remote.BooksDataSource
import io.reactivex.rxjava3.core.Single

class BooksRepoImpl(private val dataSource: BooksDataSource): BooksRepo {
    override suspend fun getAvailableBooks(): Books = dataSource.getAvailablesbooks()
}