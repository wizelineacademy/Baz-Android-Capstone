package com.jpgl.cryptocurrencies.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.BookModel
import com.jpgl.cryptocurrencies.data.model.TickerModel
import com.jpgl.cryptocurrencies.domain.GetAvailableBookUseCase
import com.jpgl.cryptocurrencies.domain.GetBidsUseCase
import com.jpgl.cryptocurrencies.domain.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val getAvailableBookUseCase : GetAvailableBookUseCase,
    private val getBidsUseCase : GetBidsUseCase,
    private val getTickerUseCase : GetTickerUseCase
) : ViewModel() {

    val bookModel = MutableLiveData<List<BookModel>>()
    val bidsModel = MutableLiveData<List<BidsModel>>()
    val tickerModel = MutableLiveData<TickerModel>()

    //Llamadas al caso de uso
    fun onCreateAvailableBook(){
        viewModelScope.launch {
            val result = getAvailableBookUseCase()
            bookModel.postValue(result)
        }
    }

    fun onCreateBids(){
        viewModelScope.launch {
            val result = getBidsUseCase()
            bidsModel.postValue(result)
        }
    }

    fun onCreateTicker(){
        viewModelScope.launch {
            val result = getTickerUseCase()
            tickerModel.postValue(result)
        }
    }

}