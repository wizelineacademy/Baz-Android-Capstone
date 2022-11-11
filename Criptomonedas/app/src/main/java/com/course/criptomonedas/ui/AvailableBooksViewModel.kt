package com.course.criptomonedas.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.criptomonedas.data.db.dao.BooksDao
import com.course.criptomonedas.data.db.model.BooksEntity
import com.course.criptomonedas.domain.GetAvailableBooksCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableBooksViewModel @Inject constructor(
    private val useCaseGetAvailableBooksCase: GetAvailableBooksCase,
    private val dao: BooksDao
) : ViewModel() {

    private val _available_books = MutableLiveData<List<BooksEntity>>()
    val availableBooks = _available_books

    fun getAvailableBooks() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = useCaseGetAvailableBooksCase()
            val entities = result.map { currency ->
                BooksEntity(
                    name = currency.book
                )
            }
            dao.insertBook(books = entities)
            val localData = dao.getBooks()

            _available_books.postValue(localData)
        } catch (e: Exception) {
            Log.d("TAG_FRANK", "getAvailableBooks: Error")
        }
    }
}