package com.example.cryptocurrencyapp.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.use_case.DetailUseCase
import com.example.cryptocurrencyapp.utils.Resource
import kotlinx.coroutines.launch

class DetailViewModel (private val detailUseCase: DetailUseCase): ViewModel() {

    private val _tickerBook = MutableLiveData<WCCTickerDTO>()
    val resumeTicker: LiveData<WCCTickerDTO> get() = _tickerBook

    private var _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _orderBok = MutableLiveData<WCCOrdeRDTO>()
    val resumeOrder: LiveData<WCCOrdeRDTO> get() = _orderBok

    fun getTicker(book:String){
        viewModelScope.launch {
            val response =detailUseCase.ticker(book)
            response.collect{ ticker ->
                when(ticker){
                    is Resource.Loading ->
                        _isLoading.value = true
                    is Resource.Success ->{
                        _tickerBook.value = ticker.data ?: WCCTickerDTO()
                        _isLoading.value = true
                                Log.i("datos","$_tickerBook")
                    }
                    is Resource.Error ->
                        Log.i("depur","Error")
                }
            }
        }
    }

    fun getOrderBook(book: String) {
        viewModelScope.launch {
            val response = detailUseCase.order(book)
            response.collect { order ->
                when (order) {
                    is Resource.Loading ->
                       _isLoading.value = true
                    is Resource.Success ->{
                        _orderBok.value = order.data ?: WCCOrdeRDTO()
                        _isLoading.value = false
                        Log.i("data", "$_orderBok")
                    }
                    is Resource.Error ->
                        Log.i("depur", "Error")
                }
            }
        }
    }


}
class ViewModelFactoryTicker(private val detaiUseCase: DetailUseCase ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(detaiUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}