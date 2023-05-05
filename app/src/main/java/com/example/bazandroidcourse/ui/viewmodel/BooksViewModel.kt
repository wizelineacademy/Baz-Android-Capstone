package com.example.bazandroidcourse.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazandroidcourse.data.di.ApplicationScope
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies
import com.example.bazandroidcourse.domain.GetAllBooksFilteredUseCase
import com.example.bazandroidcourse.domain.GetBookDetailUseCase
import com.example.bazandroidcourse.domain.GetBookOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetAllBooksFilteredUseCase,
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val getBookOrdersUseCase: GetBookOrdersUseCase,
    @ApplicationScope private val externalScope: CoroutineScope
) : ViewModel() {

    private val _allBooks : MutableState<List<BookModel>> = mutableStateOf( emptyList())
    val allBooks = _allBooks

    private val _currentBook = MutableLiveData<BookDetailModel>()
    val currentBook: MutableLiveData<BookDetailModel> = _currentBook

    private val _currentBookOrders = MutableLiveData<BookOrdersModel>()
    val currentBookOrders: MutableLiveData<BookOrdersModel> = _currentBookOrders

    private val _currentCurrency = MutableLiveData("usd")
    val currentCurrency = _currentCurrency

    val names: List<String> = ApplicationCurrencies.supportedCurrencies
        .filter { it.trading }.map { it.name }
    init {
        getAllBooks(currentCurrency.value!!)
    }

    fun getAllBooks(currency: String) {
        viewModelScope.launch(externalScope.coroutineContext) {
            _allBooks.value = getBooksUseCase.invoke(currency)
        }
    }

    fun getAllBooksByCurrency(className: String) {
        ApplicationCurrencies.findByName(className)?.let {
            getAllBooks(it.id)
        }
    }

    fun getBookDetail(bookId: String) {
        viewModelScope.launch(externalScope.coroutineContext) {
            _currentBook.postValue(
                getBookDetailUseCase.invoke(bookId)
            )
        }
    }

    fun getBookOrders(bookId: String) {
        viewModelScope.launch(externalScope.coroutineContext) {
            _currentBookOrders.postValue(
                getBookOrdersUseCase.invoke(bookId)
            )
        }
    }
}
