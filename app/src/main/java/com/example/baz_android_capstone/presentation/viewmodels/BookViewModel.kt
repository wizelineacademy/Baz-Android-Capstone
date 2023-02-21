package com.example.baz_android_capstone.presentation.viewmodels // ktlint-disable package-name

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.baz_android_capstone.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val bookName = mutableStateOf("")

    val getBooks = repository.getBooks()

    fun getOrders(book: String) = repository.getOrders(book)
    fun getTicker(book: String) = repository.getTicker(book)
    val getOrders = repository.getOrders("btc_mxn")
    val getTicker = repository.getTicker("btc_mxn")
}
