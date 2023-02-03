package com.wizeline.criptocurrency.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.criptocurrency.common.adapters.RequestState
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.model.OrderBook
import com.wizeline.criptocurrency.domain.model.Ticker
import com.wizeline.criptocurrency.domain.model.use_case.AvailableBooksUseCase
import com.wizeline.criptocurrency.domain.model.use_case.OrderBookUseCase
import com.wizeline.criptocurrency.domain.model.use_case.TickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CriptoCurrencyViewModel @Inject constructor(
    private val availableBooksUseCase: AvailableBooksUseCase,
    private val tickerUseCase: TickerUseCase,
    private val orderBookUseCase: OrderBookUseCase
) : ViewModel() {

    private var _availableOrderBookList = MutableLiveData<List<AvailableBook>>()
    private var _selectedOrderBookName = MutableLiveData<String>()
    private var _selectedCoinName = MutableLiveData<String>()

    private var _ticker = MutableLiveData<Ticker>()
    private var _orderBook = MutableLiveData<OrderBook?>()
    private var _isLoading = MutableLiveData<Boolean>(true)
    private var _error = MutableLiveData<String>()

    val availableOrderBookList: LiveData<List<AvailableBook>> get() = _availableOrderBookList
    val selectedOrderBook: LiveData<String> get() = _selectedOrderBookName
    val selectedCoinName: LiveData<String> get() = _selectedCoinName
    val ticker: LiveData<Ticker> get() = _ticker
    val orderBook: LiveData<OrderBook?> get() = _orderBook
    val isLoading: LiveData<Boolean> get() = _isLoading
    val error: LiveData<String> get() = _error


    fun setSelectedOrderBook(book: String) {
        _selectedOrderBookName.value = book
    }

    fun setSelectedCoinName(coinName: String) {
        _selectedCoinName.value = coinName
    }


    //Crear observable para manejar errores
    //Separar ViewModel para cada fragment

    fun getAvailableBooksCoroutine() {
        CoroutineScope(Dispatchers.Main).launch{
            availableBooksUseCase().onEach {
                when (it) {
                    is RequestState.Loading -> _isLoading.value = true
                    is RequestState.Success -> {
                        _availableOrderBookList.value = it.data ?: emptyList()
                        _isLoading.value = false
                    }
                    is RequestState.Error -> {
                        _error.value=it.message.toString()
                        _isLoading.value = false
                    }
                }
            }
        }

    }

    fun getAvailableBooks() {
        availableBooksUseCase().onEach {
            when (it) {
                is RequestState.Loading -> _isLoading.value = true
                is RequestState.Success -> {
                    _availableOrderBookList.value = it.data ?: emptyList()
                    _isLoading.value = false
                }
                is RequestState.Error -> {
                    _error.value=it.message.toString()
                    _isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTicker(book: String) {
        tickerUseCase.invoke(book).onEach {state->
            when (state) {
                is RequestState.Loading -> _isLoading.value = true
                is RequestState.Success -> {
                    _ticker.value = state.data?: Ticker()
                    getOrderBook(book)
                }
                is RequestState.Error -> {
                    _ticker.value = state.data?: Ticker()
                    _error.value=state.message.toString()
                    getOrderBook(book)
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getOrderBook(book: String) {
        orderBookUseCase.invoke(book).onEach {
            when (it) {
                is RequestState.Loading -> _isLoading.value = true
                is RequestState.Success -> {
                    _orderBook.value = it.data ?: OrderBook()
                    _isLoading.value = false
                }
                is RequestState.Error -> {
                    _error.value=it.message.toString()
                    _isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

}