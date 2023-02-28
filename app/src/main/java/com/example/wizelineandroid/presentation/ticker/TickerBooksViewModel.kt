package com.example.wizelineandroid.presentation.ticker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.repository.ticker.TickerRepo
import kotlinx.coroutines.Dispatchers

class TickerBooksViewModel(private val repo: TickerRepo): ViewModel() {

    fun fetchTickersBooks(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getTickerBooks(id)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class TickerViewModelFactory(private val repo: TickerRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TickerRepo::class.java).newInstance(repo)
    }

}