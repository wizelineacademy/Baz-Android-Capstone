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
import com.ari.coins.ui.uiModels.AvailableBook
import com.ari.coins.ui.uiModels.Error
import com.ari.coins.ui.uiModels.OrderBook
import com.ari.coins.ui.uiModels.Ticker
import com.ari.coins.ui.uiModels.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ari Valencia
 * @file CoinsViewModel
 * @description ViewModel for connect view with data layer
 */

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase,
    private val getTickerUseCase: GetTickerUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getCoinUrlImageUseCase: GetCoinUrlImageUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _successAvailableBooks = MutableLiveData<List<AvailableBook>>()
    val successAvailableBooks: LiveData<List<AvailableBook>> get() = _successAvailableBooks

    private val _errorAvailableBooks = MutableLiveData<Error?>()
    val errorAvailableBooks: LiveData<Error?> get() = _errorAvailableBooks

    private val _successTicker = MutableLiveData<Ticker?>()
    val successTicker: LiveData<Ticker?> get() = _successTicker

    private val _errorTicker = MutableLiveData<Error?>()
    val errorTicker: LiveData<Error?> get() = _errorTicker

    private val _successOrderBook = MutableLiveData<OrderBook?>()
    val successOrderBook: LiveData<OrderBook?> get() = _successOrderBook

    private val _errorOrderBook = MutableLiveData<Error?>()
    val errorOrderBook: LiveData<Error?> get() = _errorOrderBook

    fun clearCoinDetailView() {
        _errorTicker.value = null
        _successTicker.value = null
        _errorOrderBook.value = null
        _successOrderBook.value = null
        _errorAvailableBooks.value = null
    }

    fun getAvailableBooks() = viewModelScope.launch(dispatcher) {
        when (val data = getAvailableBooksUseCase(null)) {
            is ResultDomain.Error -> _errorAvailableBooks.postValue(Error(data.message, data.code))
            is ResultDomain.Success -> _successAvailableBooks.postValue(data.data.map { it.toUi() })
        }
    }

    fun getTicker(book: String) = viewModelScope.launch(dispatcher) {
        when (val data = getTickerUseCase(book)) {
            is ResultDomain.Error -> _errorTicker.postValue(Error(data.message, data.code))
            is ResultDomain.Success -> _successTicker.postValue(data.data.toUi())
        }
    }

    fun getOrderBook(book: String) = viewModelScope.launch(dispatcher) {
        when (val data = getOrderBookUseCase(book)) {
            is ResultDomain.Error -> _errorOrderBook.postValue(Error(data.message, data.code))
            is ResultDomain.Success -> _successOrderBook.postValue(data.data.toUi())
        }
    }

    fun getCoinUrlImage(book: String): String = getCoinUrlImageUseCase(book)
}
