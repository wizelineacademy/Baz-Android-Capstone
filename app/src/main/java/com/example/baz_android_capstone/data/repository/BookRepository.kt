package com.example.baz_android_capstone.data.repository

import android.util.Log
import com.example.baz_android_capstone.data.dataOrException.DataOrException
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.ticker.Ticker
import com.example.baz_android_capstone.data.network.BookAPI
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BookAPI) {
    private val booksOrException = DataOrException<Book, Boolean, Exception>()
    private val tickerOrException = DataOrException<Ticker, Boolean, Exception>()
    private val orderOrException = DataOrException<OrderBook, Boolean, Exception>()

    suspend fun getAllBooks(): DataOrException<Book, Boolean, Exception> {
        try {
            booksOrException.data = api.getAllAvailableBooks()
        } catch (e: Exception) {
            booksOrException.exception = e
            Log.d("Exc", "getAllBooks: ${booksOrException.exception!!.localizedMessage}")
        }
        return booksOrException
    }

    suspend fun getTicker(book: String): DataOrException<Ticker, Boolean, Exception> {
        try {
            tickerOrException.data = api.getTicker(book)
        } catch (e: Exception) {
            tickerOrException.exception = e
            Log.d("Exc", "getAllBooks: ${tickerOrException.exception!!.localizedMessage}")
        }
        return tickerOrException
    }

    suspend fun getOrder(book: String): DataOrException<OrderBook, Boolean, Exception> {
        try {
            orderOrException.data = api.getOrderBook(book)
        } catch (e: Exception) {
            orderOrException.exception = e
            Log.d("Exc", "getAllBooks: ${orderOrException.exception!!.localizedMessage}")
        }
        return orderOrException
    }
}