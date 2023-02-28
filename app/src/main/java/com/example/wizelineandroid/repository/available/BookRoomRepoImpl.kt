package com.example.wizelineandroid.repository.available

import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.entitys.BookEntity

class BookRoomRepoImpl(private val dataRoom: BookDao): BookRoomRepo {

    override suspend fun insertBooks(item: List<BookEntity>) = dataRoom.insert(item)

    override suspend fun getBooks(): List<BookEntity> = dataRoom.getBooks()

}