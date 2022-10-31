package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.use_case.TickerUseCase
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.launch

class TickerViewModel (private val tickerUseCase: TickerUseCase): ViewModel() {

    private val _tickerBook = MutableLiveData<WCCTickerDTO>()
    val resumeTicker: LiveData<WCCTickerDTO> get() = _tickerBook

    fun getTicker(book:String){
        viewModelScope.launch {
            val response = tickerUseCase.ticker(book)
            response.collect{ ticker ->
                when(ticker){
                    is Resource.Loading ->
                        Log.i("depur","cargando")
                    is Resource.Success ->{
                        _tickerBook.value = ticker.data ?: WCCTickerDTO()
                                Log.i("datos","$_tickerBook")
                    }
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