package com.ari.coins.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.coins.data.models.AvailableBook
import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.Ticker
import com.ari.coins.domain.GetAvailableBooksUseCase
import com.ari.coins.domain.GetCoinUrlImageUseCase
import com.ari.coins.domain.GetOrderBookUseCase
import com.ari.coins.domain.GetTickerUseCase
import com.ari.coins.ui.models.ResultUi
import com.ari.coins.ui.models.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase,
    private val getTickerUseCase: GetTickerUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getCoinUrlImageUseCase: GetCoinUrlImageUseCase
): ViewModel() {

    private val _availableBooks = MutableLiveData<ResultUi<List<AvailableBook>>>()
    val availableBooks: LiveData<ResultUi<List<AvailableBook>>> get() = _availableBooks

    private val _ticker = MutableLiveData<ResultUi<Ticker>>()
    val ticker: LiveData<ResultUi<Ticker>> get() = _ticker

    private val _orderBook = MutableLiveData<ResultUi<OrderBook>>()
    val orderBook: LiveData<ResultUi<OrderBook>> get() = _orderBook

    fun clearCoinDetailView() {
        _ticker.value = ResultUi.Empty()
        _orderBook.value = ResultUi.Empty()
    }

    fun getAvailableBooks() = viewModelScope.launch{
        val result: ResultUi<List<AvailableBook>> = getAvailableBooksUseCase(null).toUi()
        _availableBooks.postValue(result)
    }

    fun getTicker(book: String) = viewModelScope.launch{
        val result: ResultUi<Ticker> = getTickerUseCase(book).toUi()
        _ticker.postValue(result)
    }

    fun getOrderBook(book: String) = viewModelScope.launch{
        val result: ResultUi<OrderBook> = getOrderBookUseCase(book).toUi()
        _orderBook.postValue(result)
    }

    fun getCoinUrlImage(book: String): String = getCoinUrlImageUseCase(book)

}