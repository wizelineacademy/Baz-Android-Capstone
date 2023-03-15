package com.example.wizelineandroid.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.repository.order.OrderBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class OrderBooksViewModel @Inject constructor(private val repo: OrderBookRepo) : ViewModel() {

    // Usamos los estados de carga
    fun fetchOrder(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getOrderBooks(id)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}


