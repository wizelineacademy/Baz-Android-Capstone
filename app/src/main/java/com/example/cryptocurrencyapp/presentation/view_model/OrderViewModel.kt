package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.domain.use_case.OrderUseCase
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.launch

class OrderViewModel(private val orderUseCase: OrderUseCase) : ViewModel() {
    fun getOrderBook(book: String) {
        viewModelScope.launch {
            val response = orderUseCase.order(book)
            response.collect { order ->
                when (order) {
                    is Resource.Loading ->
                        Log.i("depur", "cargando")
                    is Resource.Success ->
                        Log.i("data", "$order")
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