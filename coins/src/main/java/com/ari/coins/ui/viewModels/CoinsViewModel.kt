package com.ari.coins.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.coins.domain.GetAvailableBooksUseCase
import com.ari.coins.domain.GetCoinUrlImageUseCase
import com.ari.coins.domain.GetOrderBookUseCase
import com.ari.coins.domain.GetTickerUseCase
import com.ari.coins.domain.domainModels.ResultDomain
import com.ari.coins.ui.uiModels.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase,
    private val getTickerUseCase: GetTickerUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getCoinUrlImageUseCase: GetCoinUrlImageUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _availableBooks = MutableLiveData<Result<List<AvailableBook>>>()
    val availableBooks: LiveData<Result<List<AvailableBook>>> get() = _availableBooks

    private val _ticker = MutableLiveData<Result<Ticker>>()
    val ticker: LiveData<Result<Ticker>> get() = _ticker

    private val _orderBook = MutableLiveData<Result<OrderBook>>()
    val orderBook: LiveData<Result<OrderBook>> get() = _orderBook

    fun clearCoinDetailView() {
        _ticker.value = Result.Empty()
        _orderBook.value = Result.Empty()
    }

    fun getAvailableBooks() = viewModelScope.launch(dispatcher) {
        val result = when(val data = getAvailableBooksUseCase(null)) {
            is ResultDomain.Error -> Result.Error(data.message, data.code)
            is ResultDomain.Success -> Result.Success(data.data.map { it.toUi() })
        }
        _availableBooks.postValue(result)
    }

    fun getTicker(book: String) = viewModelScope.launch(dispatcher) {
        val result = when(val data = getTickerUseCase(book)) {
            is ResultDomain.Error -> Result.Error(data.message, data.code)
            is ResultDomain.Success -> Result.Success(data.data.toUi())
        }
        _ticker.postValue(result)
    }

    fun getOrderBook(book: String) = viewModelScope.launch(dispatcher) {
        val result = when(val data = getOrderBookUseCase(book)) {
            is ResultDomain.Error -> Result.Error(data.message, data.code)
            is ResultDomain.Success -> Result.Success(data.data.toUi())
        }
        _orderBook.postValue(result)
    }

    fun getCoinUrlImage(book: String): String = getCoinUrlImageUseCase(book)

}