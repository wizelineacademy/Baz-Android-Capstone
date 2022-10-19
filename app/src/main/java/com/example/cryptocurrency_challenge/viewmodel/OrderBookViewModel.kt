package com.example.cryptocurrency_challenge.viewmodel

import androidx.lifecycle.*
import com.example.cryptocurrency_challenge.domain.OrderBookUseCase
import com.example.cryptocurrency_challenge.utils.OrderBookUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(private val orderBookUseCase: OrderBookUseCase):ViewModel(){

    private val _orderBookModel : MutableLiveData<OrderBookUiState>  = MutableLiveData()
    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()

    val orderBookModel  : LiveData<OrderBookUiState> = _orderBookModel
    val isLoading           : LiveData<Boolean> = _isLoading


    fun getOrderBook(currency_name: String){
        viewModelScope.launch {
            val result = orderBookUseCase(currency_name)
            result.let {
                _isLoading.postValue(false)
                _orderBookModel.value = OrderBookUiState(askList = result )
            }
        }
    }
}

