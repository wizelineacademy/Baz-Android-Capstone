package com.example.bazandroidcourse.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andcourse.domain.model.BookDetailModel
import com.andcourse.domain.model.BookModel
import com.andcourse.domain.model.BookOrdersModel
import com.andcourse.domain.model.staticdata.ApplicationCurrency
import com.androidcourse.data.GetAllBooksFilteredUseCase
import com.androidcourse.data.GetBookDetailUseCase
import com.androidcourse.data.GetBookOrdersUseCase
import com.androidcourse.data.di.ApplicationScope
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
    var allBooks = _allBooks

    private val _currentBook = mutableStateOf(BookModel())
    val currentBook: MutableState<BookModel> = _currentBook

    private val _currentBookDetail = mutableStateOf(BookDetailModel())
    val currentBookDetail:MutableState<BookDetailModel> = _currentBookDetail

    private val _currentBookOrders = MutableLiveData<BookOrdersModel>()
    val currentBookOrders: MutableLiveData<BookOrdersModel> = _currentBookOrders

    private val _currentCurrency:MutableState<ApplicationCurrency> = mutableStateOf(ApplicationCurrency.USD)
    val currentCurrency = _currentCurrency
    val supportedCurrencies = ApplicationCurrency.supportedCurrencies.filter { it.trading }

    init {
        getAllBooks(currentCurrency.value.id)
    }

    fun getAllBooks(currency: String) {
        viewModelScope.launch(externalScope.coroutineContext) {
           val  results = getBooksUseCase.invoke(currency)
            _allBooks.value = results
        }
    }

    fun getAllBooksByCurrency() {
        getAllBooks(currentCurrency.value.id)
    }

    fun getBookDetail(bookId: String) {
        viewModelScope.launch(externalScope.coroutineContext) {
            _currentBookDetail.value =
                getBookDetailUseCase.invoke(bookId)
        }
    }

    fun getBookOrders(bookId: String) {
        viewModelScope.launch(externalScope.coroutineContext) {
            _currentBookOrders.postValue(
                getBookOrdersUseCase.invoke(bookId)
            )
        }
    }

    fun setSelectBook(it: BookModel) {
        _currentBook.value = it
    }
}
