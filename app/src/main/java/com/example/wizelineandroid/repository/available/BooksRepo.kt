package com.example.wizelineandroid.repository.available

import com.example.wizelineandroid.data.remote.model.Books

interface BooksRepo {
    suspend fun getAvailableBooks(): Books
}
