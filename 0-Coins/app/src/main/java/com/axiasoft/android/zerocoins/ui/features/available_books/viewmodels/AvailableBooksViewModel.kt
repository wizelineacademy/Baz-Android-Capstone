package com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels

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

    val books: MutableLiveData<MutableList<ExchangeOrderBook>> by lazy {
        MutableLiveData<MutableList<ExchangeOrderBook>>()
    }

    var selectedBookOrder = ExchangeOrderBook()

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
                    val stuff = booksState.data
                    books.postValue(stuff)
                }
                is BooksScreenState.BooksErrorOrEmpty -> {
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
                    books.postValue(booksState.data)
                }
                is BooksScreenState.BooksErrorOrEmpty -> {
                    log(message = "some error ${booksState.message}")
                }
            }
        }
    }
}