package com.example.wizelineandroid.repository.available

import com.example.wizelineandroid.data.model.Books

interface BooksRepo {
    suspend fun getAvailableBooks(): Books
}