package com.example.cryptocurrency_challenge.viewmodel

import androidx.lifecycle.*
import com.example.cryptocurrency_challenge.domain.TickerUseCase
import com.example.cryptocurrency_challenge.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(private val tickerUseCase: TickerUseCase):ViewModel(){
//class TickerViewModel (private val tickerUseCase: TickerUseCase): ViewModel() {

    private val _payLoadTicker      : MutableLiveData <TickerUiState> = MutableLiveData()
    private val _isLoading          : MutableLiveData<Boolean> = MutableLiveData()

    val payLoadTicker       : LiveData <TickerUiState> = _payLoadTicker
    val isLoading           : LiveData<Boolean> = _isLoading


    fun geTicker(currency_name: String?) {
        viewModelScope.launch {
            val result = tickerUseCase.invoke(currency_name)
            result.let {
                _isLoading.postValue(false)
                _payLoadTicker.value = TickerUiState(payLoadTicker = result.payload)
            }
        }

    }
}

class TickerViewModelFactory(private val tickerUseCase: TickerUseCase) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TickerUseCase::class.java)
            .newInstance(tickerUseCase)
    }
}



