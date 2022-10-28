package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.domain.use_case.TickerUseCase
import com.example.cryptocurrencyapp.domain.use_case.WCCAvailableUseCase
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TickerViewModel (private val tickerUseCase: TickerUseCase): ViewModel() {
    fun getTicker(book:String){
        viewModelScope.launch {
            val response = tickerUseCase.ticker(book)
            response.collect{ ticker ->
                when(ticker){
                    is Resource.Loading ->
                        Log.i("depur","cargando")
                    is Resource.Success ->
                        Log.i("datos","$ticker")
                    is Resource.Error ->
                        Log.i("depur","Error")
                }
            }
        }
    }
}
class ViewModelFactoryTicker(private val tickerUseCase: TickerUseCase ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TickerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TickerViewModel(tickerUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}