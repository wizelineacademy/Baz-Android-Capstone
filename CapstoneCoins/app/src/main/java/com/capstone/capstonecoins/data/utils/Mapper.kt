package com.capstone.capstonecoins.data.utils

import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.data.models.availablebooks.Payload
import com.capstone.capstonecoins.data.models.ticker.tickerquery.TickerWithQuery
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.repository.models.BookDetail

fun BooksDto.toBooks(): List<Book> =
    this.payload.map {
        it.toBook()
    }


fun TickerWithQuery.toDetail() =
    BookDetail(high = this.payload.high, last = this.payload.last, low = this.payload.low)

fun Payload.toBook() = Book(id = book)


