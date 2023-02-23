package com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.toDomain
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases.GetListOrderBookUseCase
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.use_cases.GetTickerUseCase
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.TickerScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepositoryImpl: LocalOrderBookRepository
) : ViewModel() {

    var selectedBookOrder = ExchangeOrderBook()

    var tickerState: MutableLiveData<TickerScreenState> = MutableLiveData()

    var listOrderBookScreenState: MutableLiveData<ListOrderBookScreenState> = MutableLiveData()

    fun getRemoteTicker() {
        viewModelScope.launch {
            val tickerSreenState = GetTickerUseCase(
                remoteOrderBooksRepository,
                localOrderBookRepositoryImpl
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
                localOrderBookRepositoryImpl
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
                localOrderBookRepositoryImpl
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
                localOrderBookRepositoryImpl
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


    fun call(){
        val sch = Schedulers.newThread()
        val x = remoteOrderBooksRepository.getTickerFromApiObservable(book = selectedBookOrder.book ?: "")
            .subscribeOn(sch)
            .observeOn(sch)
            .subscribe {
                if (it.success == true) {
                    val ticker = it.payload?.toDomain()
                    tickerState.value = TickerScreenState.TickerSuccess(ticker ?: Ticker())
                } else {

                }
            }
    }
}