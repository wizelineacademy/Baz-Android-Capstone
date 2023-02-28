package com.example.wizelineandroid.presentation.order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.repository.order.OrderBookRepo
import kotlinx.coroutines.Dispatchers

class OrderBooksViewModel( private val repo: OrderBookRepo): ViewModel() {

    //Usamos los estados de carga
    fun fetchOrder(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getOrderBooks(id)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class OrderViewModelFactory(private val repo: OrderBookRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(OrderBookRepo::class.java).newInstance(repo)
    }

}