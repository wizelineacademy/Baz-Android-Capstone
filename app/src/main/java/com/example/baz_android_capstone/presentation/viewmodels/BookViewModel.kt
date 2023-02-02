package com.example.baz_android_capstone.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.baz_android_capstone.data.dataOrException.DataOrException
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.ticker.Ticker
import com.example.baz_android_capstone.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    suspend fun getOrder(book: String): DataOrException<OrderBook, Boolean, Exception> {
        return repository.getOrder(book)
    }

    suspend fun getTicker(book: String): DataOrException<Ticker, Boolean, Exception> {
        return repository.getTicker(book)
    }

    suspend fun getAllBooks(): DataOrException<Book, Boolean, Exception> {
        return repository.getAllBooks()
    }
}