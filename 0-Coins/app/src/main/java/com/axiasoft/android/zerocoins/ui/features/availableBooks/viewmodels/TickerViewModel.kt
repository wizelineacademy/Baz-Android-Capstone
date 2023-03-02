package com.axiasoft.android.zerocoins.ui.features.availableBooks.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.useCases.GetListOrderBookUseCase
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.useCases.GetTickerUseCase
import com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates.TickerScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepositoryImpl: LocalOrderBookRepository,
) : ViewModel() {

    var selectedBookOrder = ExchangeOrderBook()

    var tickerState: MutableLiveData<TickerScreenState> = MutableLiveData()

    var listOrderBookScreenState: MutableLiveData<ListOrderBookScreenState> = MutableLiveData()

    fun getRemoteTicker() {
        viewModelScope.launch {
            val tickerSreenState = GetTickerUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl,
            ).invoke(selectedBookOrder)
            when (tickerSreenState) {
                is TickerScreenState.TickerSuccess -> {
                    val ticker = tickerSreenState.ticker
                    tickerState.value = TickerScreenState.TickerSuccess(ticker)
                }
                is TickerScreenState.TickerError -> {
                }
            }
        }
    }

    fun getLocalTicker() {
        viewModelScope.launch {
            val tickerScreenState = GetTickerUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl,
            ).retrieveTicker(selectedBookOrder)
            when (tickerScreenState) {
                is TickerScreenState.TickerSuccess -> {
                    val ticker = tickerScreenState.ticker
                    tickerState.value = TickerScreenState.TickerSuccess(ticker)
                }
                is TickerScreenState.TickerError -> {
                }
            }
        }
    }

    fun getRemoteListOrderBook() {
        viewModelScope.launch {
            val listOrderBookState = GetListOrderBookUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl,
            ).invoke(selectedBookOrder)
            when (listOrderBookState) {
                is ListOrderBookScreenState.Success -> {
                    listOrderBookScreenState.value = listOrderBookState
                }
                is ListOrderBookScreenState.ErrorOrEmpty -> {
                }
            }
        }
    }

    fun getLocalListOrderBook() {
        viewModelScope.launch {
            val listOrderBookState = GetListOrderBookUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl,
            ).retrieveAskAndBids(selectedBookOrder)
            when (listOrderBookState) {
                is ListOrderBookScreenState.Success -> {
                    listOrderBookScreenState.value = listOrderBookState
                }
                is ListOrderBookScreenState.ErrorOrEmpty -> {
                }
            }
        }
    }
}
