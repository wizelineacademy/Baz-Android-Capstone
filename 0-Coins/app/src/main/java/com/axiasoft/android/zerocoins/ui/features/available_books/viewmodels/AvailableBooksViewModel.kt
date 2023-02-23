package com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases.GetBooksUseCase
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.BooksScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableBooksViewModel @Inject constructor(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepositoryImpl: LocalOrderBookRepository
) : ViewModel() {

    private val _books: MutableLiveData<MutableList<ExchangeOrderBook>> by lazy {
        MutableLiveData<MutableList<ExchangeOrderBook>>()
    }
    val books: LiveData<MutableList<ExchangeOrderBook>>
    get() = _books

    var selectedBookOrder = ExchangeOrderBook()

    var availableBooksUpdatedFromRemote: Boolean = false

    fun getBooksWithNoUseCase() {
        viewModelScope.launch {
            val response = remoteOrderBooksRepository.getBooksFromApi()
            when (response) {
                is BitsoApiResponseWrap.Success -> {
                    if (response.response.success == true) {
                        log(message = "Hi array of books:-> ${response.response.payload.toString()}")
                    }
                }
                else -> {}
            }
        }
    }

    fun getExchangeOrderBooks() {
        viewModelScope.launch {
            val booksState = GetBooksUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl
            ).invoke()
            when (booksState) {
                is BooksScreenState.BooksSuccess -> {
                    availableBooksUpdatedFromRemote = true
                    val stuff = booksState.data
                    _books.postValue(stuff)
                }
                is BooksScreenState.BooksErrorOrEmpty -> {
                    availableBooksUpdatedFromRemote = false
                    log(message = "some error ${booksState.message}")
                }
            }
        }
    }

    fun getLocalExchangeOrderBooks() {
        viewModelScope.launch {
            val booksState = GetBooksUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl
            ).retrieveExchangeOrderBook()
            when (booksState) {
                is BooksScreenState.BooksSuccess -> {
                    availableBooksUpdatedFromRemote = false
                    _books.postValue(booksState.data)
                }
                is BooksScreenState.BooksErrorOrEmpty -> {
                    availableBooksUpdatedFromRemote = false
                    log(message = "some error ${booksState.message}")
                }
            }
        }
    }
}