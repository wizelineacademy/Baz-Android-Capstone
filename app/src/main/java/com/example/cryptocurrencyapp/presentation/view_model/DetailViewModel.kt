package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.use_case.DetailUseCase
import com.example.cryptocurrencyapp.utils.Resource
import com.example.cryptocurrencyapp.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (private val detailUseCase: DetailUseCase): ViewModel() {

    private val _tickerBook = MutableLiveData<WCCTickerDTO>()
    val resumeTicker: LiveData<WCCTickerDTO> get() = _tickerBook

    private var _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _orderBok = MutableLiveData<WCCOrdeRDTO>()
    val resumeOrder: LiveData<WCCOrdeRDTO> get() = _orderBok

    fun getTicker(book:String){
        viewModelScope.launch {
            val response =detailUseCase.ticker(book)
            response.onEach{ ticker ->
                when(ticker){
                    is Resource.Loading ->
                        _isLoading.value = true
                    is Resource.Success ->{
                        _tickerBook.value = ticker.data ?: WCCTickerDTO()
                        _isLoading.value = false
                                Log.i("datos","$_tickerBook")
                    }
                    is Resource.Error ->
                       Utils.showDialog()
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getOrderBook(book: String) {
        viewModelScope.launch {
            val response = detailUseCase.order(book)
            response.onEach { order ->
                when (order) {
                    is Resource.Loading ->
                       _isLoading.value = true
                    is Resource.Success ->{
                        _orderBok.value = order.data ?: WCCOrdeRDTO()
                        _isLoading.value = false
                        Log.i("data", "$_orderBok")
                    }
                    is Resource.Error ->
                        Utils.showDialog()
                }
            }.launchIn(viewModelScope)
        }
    }
}
