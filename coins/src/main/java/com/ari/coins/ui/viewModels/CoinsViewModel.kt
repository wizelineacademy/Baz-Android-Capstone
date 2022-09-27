package com.ari.coins.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.TickerData
import com.ari.coins.domain.GetAvailableBooksUseCase
import com.ari.coins.domain.GetCoinUrlImageUseCase
import com.ari.coins.domain.GetOrderBookUseCase
import com.ari.coins.domain.GetTickerUseCase
import com.ari.coins.ui.uiModels.Result
import com.ari.coins.ui.uiModels.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase,
    private val getTickerUseCase: GetTickerUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getCoinUrlImageUseCase: GetCoinUrlImageUseCase
): ViewModel() {

    private val _availableBooks = MutableLiveData<Result<List<AvailableBookData>>>()
    val availableBooks: LiveData<Result<List<AvailableBookData>>> get() = _availableBooks

    private val _ticker = MutableLiveData<Result<TickerData>>()
    val ticker: LiveData<Result<TickerData>> get() = _ticker

    private val _orderBook = MutableLiveData<Result<OrderBookData>>()
    val orderBook: LiveData<Result<OrderBookData>> get() = _orderBook

    fun clearCoinDetailView() {
        _ticker.value = Result.Empty()
        _orderBook.value = Result.Empty()
    }

    fun getAvailableBooks() = viewModelScope.launch(Dispatchers.IO){
        val result: Result<List<AvailableBookData>> = getAvailableBooksUseCase(null).toUi()
        _availableBooks.postValue(result)
    }

    fun getTicker(book: String) = viewModelScope.launch(Dispatchers.IO){
        val result: Result<TickerData> = getTickerUseCase(book).toUi()
        _ticker.postValue(result)
    }

    fun getOrderBook(book: String) = viewModelScope.launch(Dispatchers.IO){
        val result: Result<OrderBookData> = getOrderBookUseCase(book).toUi()
        _orderBook.postValue(result)
    }

    fun getCoinUrlImage(book: String): String = getCoinUrlImageUseCase(book)

}