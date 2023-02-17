package com.example.baz_android_capstone.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baz_android_capstone.data.dataOrException.DataOrException
import com.example.baz_android_capstone.data.models.roomModel.BookDetails
import com.example.baz_android_capstone.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
@HiltViewModel
class BookViewModel @Inject constructor(private val repository: BookRepositoryInterface) : ViewModel() {

    suspend fun getOrder(book: String): DataOrException<OrderBook, Boolean, Exception> {
        return repository.getOrder(book)
    }

    suspend fun getTicker(book: String): DataOrException<Ticker, Boolean, Exception> {
        return repository.getTicker(book)
    }

    suspend fun getAllBooks(): DataOrException<Book, Boolean, Exception> {
        return repository.getAllBooks()
    }
}*/

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    /*val books: MutableState<DataOrException<List<BookDetails>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(
                data = null,
                loading = true,
                exception = Exception("")
            )
        )

    init {
        getBooks()
        Log.d("TAG2", "$books ")
    }

    private fun getBooks() {
        viewModelScope.launch {
            books.value.loading = true
            books.value.data = repository.getBooks()
            Log.d("TAG", "getBooks: ${books.value.data}")
            if (books.value.data.toString().isNotEmpty()) {
                books.value.loading = false
            }
        }
    }*/
    suspend fun getBooks(): List<BookDetails> {
        return repository.getBooks()
    }
}
