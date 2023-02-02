package com.example.baz_android_capstone.data.repository

import com.example.baz_android_capstone.data.dataOrException.DataOrException
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.ticker.Ticker

interface BookRepositoryInterface {
    suspend fun getAllBooks(): DataOrException<Book, Boolean, Exception>
    suspend fun getTicker(book: String): DataOrException<Ticker, Boolean, Exception>
    suspend fun getOrder(book: String): DataOrException<OrderBook, Boolean, Exception>
}