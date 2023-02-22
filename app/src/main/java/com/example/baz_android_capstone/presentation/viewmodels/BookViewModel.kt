package com.example.baz_android_capstone.presentation.viewmodels // ktlint-disable package-name

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.repository.Repository
import com.example.baz_android_capstone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val bookName = mutableStateOf("")

    // val getBooks = repository.getBooks()

    private val _books = MutableStateFlow<Resource<Book>>(Resource.Loading(null))
    val books = _books.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBooks().distinctUntilChanged().collect {
                _books.value = it
            }
        }
    }

    fun getOrders(book: String) = repository.getOrders(book)
    fun getTicker(book: String) = repository.getTicker(book)
}
