package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.use_case.OrderUseCase
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.launch

class OrderViewModel(private val orderUseCase: OrderUseCase) : ViewModel() {

    private val _orderBok = MutableLiveData<WCCOrdeRDTO>()
    val resumeOrder: LiveData<WCCOrdeRDTO> get() = _orderBok

    fun getOrderBook(book: String) {
        viewModelScope.launch {
            val response = orderUseCase.order(book)
            response.collect { order ->
                when (order) {
                    is Resource.Loading ->
                        Log.i("depur", "cargando")
                    is Resource.Success ->{
                        _orderBok.value = order.data ?: WCCOrdeRDTO()
                        Log.i("data", "$_orderBok")
                    }
                    is Resource.Error ->
                        Log.i("depur", "Error")
                }
            }
        }
    }
}

class ViewModelFactoryOrder (private val orderUseCase: OrderUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrderViewModel(orderUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}