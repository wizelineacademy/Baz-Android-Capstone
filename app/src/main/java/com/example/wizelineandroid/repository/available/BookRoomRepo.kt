package com.example.wizelineandroid.repository.available

import com.example.wizelineandroid.data.local.entitys.BookEntity

interface BookRoomRepo {

    suspend fun insertBooks(item: List<BookEntity>)

    suspend fun getBooks(): List<BookEntity>
}
