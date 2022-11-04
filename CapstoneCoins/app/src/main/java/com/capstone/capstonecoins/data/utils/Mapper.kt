package com.capstone.capstonecoins.data.utils

import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.data.models.availablebooks.Payload
import com.capstone.capstonecoins.data.repository.models.Book

fun BooksDto.toBooks(): List<Book> =
    this.payload.map {
        it.toBook()
    }


fun Payload.toBook() = Book(id = book)