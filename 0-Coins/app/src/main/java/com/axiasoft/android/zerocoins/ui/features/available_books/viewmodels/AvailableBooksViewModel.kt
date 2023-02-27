package com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axiasoft.android.zerocoins.common.log
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
    private val localOrderBookRepositoryImpl: LocalOrderBookRepository,
) : ViewModel() {

    private val _books: MutableLiveData<MutableList<ExchangeOrderBook>> by lazy {
        MutableLiveData<MutableList<ExchangeOrderBook>>()
    }
    val books: LiveData<MutableList<ExchangeOrderBook>>
        get() = _books

    var selectedBookOrder = ExchangeOrderBook()

    var availableBooksUpdatedFromRemote: Boolean = false

    fun getAvailableExchangeOrderBooks() {
        viewModelScope.launch {
            val booksState = GetBooksUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl,
            ).invoke()
            when (booksState) {
                is BooksScreenState.BooksSuccess -> {
                    availableBooksUpdatedFromRemote = true
                    val availableExchangeOrderBooks = booksState.data
                    _books.postValue(availableExchangeOrderBooks)
                }
                is BooksScreenState.BooksErrorOrEmpty -> {
                    availableBooksUpdatedFromRemote = false
                    log(message = "some error ${booksState.message}")
                }
            }
        }
    }

    fun getAvailableExchangeOrderBooksRX() {
        GetBooksUseCase(
            remoteOrderBooksRepository,
            localOrderBookRepositoryImpl,
        ).callAvailableOrderBooksRX { state ->
            if (state is BooksScreenState.BooksSuccess) {
                availableBooksUpdatedFromRemote = true
                val availableExchangeOrderBooks = state.data
                _books.postValue(availableExchangeOrderBooks)
            } else {
                availableBooksUpdatedFromRemote = false
                log(message = "some error state.message")
            }
        }
    }

    fun getLocalExchangeOrderBooks() {
        viewModelScope.launch {
            val booksState = GetBooksUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl,
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
