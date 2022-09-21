package com.example.capstoneproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel
import com.example.capstoneproject.data.model.orderBook.OrderBookModel
import com.example.capstoneproject.data.model.orderBook.OrderBookResponse
import com.example.capstoneproject.data.model.ticker.TickerModel
import com.example.capstoneproject.domain.GetAvailableBooksUseCase
import com.example.capstoneproject.domain.GetOrderBookUseCase
import com.example.capstoneproject.domain.GetTickerUseCase
import com.example.capstoneproject.utils.ResultState
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private var getAvailableBooksUseCase = GetAvailableBooksUseCase()
    private var getTickerUseCase = GetTickerUseCase()
    private var getOrderBookUseCase = GetOrderBookUseCase()

    private var _availableBooks = MutableLiveData<ResultState<List<AvailableBookModel>>>()
    val availableBooks: LiveData<ResultState<List<AvailableBookModel>>> = _availableBooks

    private val _ticker = MutableLiveData<ResultState<TickerModel>>()
    val ticker: LiveData<ResultState<TickerModel>> = _ticker

    private val _orderBook = MutableLiveData<ResultState<OrderBookModel>>()
    val orderBook: LiveData<ResultState<OrderBookModel>> = _orderBook

    fun clearDetail(){
        _ticker.value = ResultState.Loading()
        _orderBook.value = ResultState.Loading()
    }

    fun getAvailableBooks() = viewModelScope.launch {
        _availableBooks.postValue(ResultState.Loading())
        try {
            _availableBooks.postValue(ResultState.Success(getAvailableBooksUseCase()))
        } catch (e: Exception) {
            Log.e("getBooksException",e.message.toString())
            _availableBooks.postValue(ResultState.Error(message = "No se a podido recuperar la informacion del servicio"))
        }
    }


    fun getTicker(book: String) = viewModelScope.launch {
        try {
            _ticker.postValue(ResultState.Success(getTickerUseCase(book)))
        } catch (e: Exception) {
            Log.e("getTickerException",e.message.toString())
            _ticker.postValue(ResultState.Error(message = "No se a podido recuperar la informacion de detalle del servicio"))
        }
    }

    fun getOrderBook(book:String) = viewModelScope.launch {
        try {
            _orderBook.postValue(ResultState.Success(getOrderBookUseCase(book)))
        }catch (e:Exception){
            Log.e("getOrderBookException",e.message.toString())
            _ticker.postValue(ResultState.Error(message = "No se a podido recuperar la informacion del servicio"))
        }
    }


}