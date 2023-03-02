package com.axiasoft.android.zerocoins.ui.features.availableBooks.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.orderBook.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.orderBook.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.useCases.GetBooksUseCase
import com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates.BooksScreenState
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

    var booksResultForCompose: List<ExchangeOrderBook> by mutableStateOf(listOf())

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
