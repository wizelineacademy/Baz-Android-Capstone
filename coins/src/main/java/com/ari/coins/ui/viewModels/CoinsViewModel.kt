package com.ari.coins.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.coins.data.models.AvailableBooks
import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.Result
import com.ari.coins.data.models.Ticker
import com.ari.coins.domain.GetAvailableBooksUseCase
import com.ari.coins.domain.GetOrderBookUseCase
import com.ari.coins.domain.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase,
    private val getTickerUseCase: GetTickerUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase
): ViewModel() {

    private val _availableBooks = MutableLiveData<Result<List<AvailableBooks>>>()
    val availableBooks: LiveData<Result<List<AvailableBooks>>> get() = _availableBooks

    private val _ticker = MutableLiveData<Result<Ticker>>()
    val ticker: LiveData<Result<Ticker>> get() = _ticker

    private val _orderBook = MutableLiveData<Result<OrderBook>>()
    val orderBook: LiveData<Result<OrderBook>> get() = _orderBook

    fun getAvailableBooks() = viewModelScope.launch{
        val result: Result<List<AvailableBooks>> = getAvailableBooksUseCase(null)
        _availableBooks.postValue(result)
    }

    fun getTicker(book: String) = viewModelScope.launch{
        val result: Result<Ticker> = getTickerUseCase(book)
        _ticker.postValue(result)
    }

    fun getOrderBook(book: String) = viewModelScope.launch{
        val result: Result<OrderBook> = getOrderBookUseCase(book)
        _orderBook.postValue(result)
    }

}