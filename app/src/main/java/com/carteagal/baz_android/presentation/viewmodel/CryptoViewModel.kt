package com.carteagal.baz_android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carteagal.baz_android.data.model.availableBook.AvailableBookResponse
import com.carteagal.baz_android.data.model.availableBook.AvailableBookUI
import com.carteagal.baz_android.data.model.orderBook.OrderBookResponse
import com.carteagal.baz_android.data.model.tickerResponse.TickerResponse
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.network.Resources.Error
import com.carteagal.baz_android.data.network.Resources.Success
import com.carteagal.baz_android.domain.useCase.GetAvailableBooksUseCase
import com.carteagal.baz_android.domain.useCase.GetOrderBookUseCase
import com.carteagal.baz_android.domain.useCase.GetTickerUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getTickerUserCase: GetTickerUserCase
): ViewModel(){

    private val _loading = MutableLiveData<Boolean>()
    private val _availableBooks = MutableLiveData<Resources<List<AvailableBookUI>?>>()
    private val _orderBooks = MutableLiveData<Resources<List<OrderBookResponse>?>>()
    private val _ticker = MutableLiveData<Resources<TickerResponse?>>()

    val loading: LiveData<Boolean> get() = _loading
    val availableBooks: LiveData<Resources<List<AvailableBookUI>?>> get() = _availableBooks
    val orderBooks: LiveData<Resources<List<OrderBookResponse>?>> get() = _orderBooks
    val ticker: LiveData<Resources<TickerResponse?>> get() = _ticker

    fun getAvailableBooks() = viewModelScope.launch{
        _loading.postValue(true)
        val result = when(val data = getAvailableBooksUseCase()){
            is Success -> { Success(data.data) }
            is Error -> { Error(data.message) }
            else -> { Error(data.message) }
        }
        _availableBooks.postValue(result)
        _loading.postValue(false)
    }

    fun getOrderBooks(book: String) = viewModelScope.launch {
        _loading.postValue(true)
        val result = when(val data = getOrderBookUseCase(book)){
            is Success -> Success(data.data)
            is Error -> Error(data.message)
            else -> Error(data.message)
        }
        _orderBooks.postValue(result)
        _loading.postValue(false)
    }

    fun getTicker(book: String) = viewModelScope.launch {
        _loading.postValue(true)
        val result = when(val data = getTickerUserCase(book)){
            is Success -> Success(data.data)
            is Error -> Error(data.message)
            else -> Error(data.message)
        }
        _ticker.postValue(result)
        _loading.postValue(false)
    }
}