package com.example.bazandroidcourse.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies
import com.example.bazandroidcourse.domain.GetAllBooksFilteredUseCase
import com.example.bazandroidcourse.domain.GetBookDetailUseCase
import com.example.bazandroidcourse.domain.GetBookOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetAllBooksFilteredUseCase,
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val getBookOrdersUseCase: GetBookOrdersUseCase,
   // private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _allBooks = MutableLiveData<List<BookModel>>()
    val allBooks: MutableLiveData<List<BookModel>> = _allBooks

    private val _currentBook = MutableLiveData<BookDetailModel>()
    val currentBook: MutableLiveData<BookDetailModel> = _currentBook

    private val _currentBookOrders = MutableLiveData<BookOrdersModel>()
    val currentBookOrders: MutableLiveData<BookOrdersModel> = _currentBookOrders

    val names :List<String> = ApplicationCurrencies::class.sealedSubclasses.map {
        it.simpleName?:""
    }

    fun getAllBooks(currency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _allBooks.postValue(
                getBooksUseCase.invoke(currency)
            )
        }
    }

    fun getAllBooksByCurrency(className:String) {
        ApplicationCurrencies.findByName(className)?.let {
            getAllBooks(it.id)
        }
    }

    fun getBookDetail(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentBook.postValue(
                getBookDetailUseCase.invoke(bookId)
            )
        }
    }

    fun getBookOrders(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentBookOrders.postValue(
                getBookOrdersUseCase.invoke(bookId)
            )
        }
    }
}
