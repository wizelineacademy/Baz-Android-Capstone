package com.course.criptomonedas.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.course.criptomonedas.data.models.Ask
import com.course.criptomonedas.data.models.Bid
import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.domain.GetDetailBookCase
import com.course.criptomonedas.domain.GetOrderBookCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailBookViewModel(
    private val useCaseGetDetailsBooksCase: GetDetailBookCase,
    private val useCaseGetOrderBookCase: GetOrderBookCase
) : ViewModel() {
    private val _detail_books = MutableLiveData<ModelDetails>()
    val detailBooks = _detail_books

    private val _order_bids = MutableLiveData<List<Bid>>()
    val orderBids = _order_bids

    private val _order_asks = MutableLiveData<List<Ask>>()
    val orderAsks = _order_asks


    fun getAvailableBooks(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = useCaseGetDetailsBooksCase(id)
            _detail_books.postValue(result)
        } catch (e: Exception) {
            Log.d("TAG_FRANK", "getAvailableBooks: Error")
        }
    }

    fun getOrderBooks(book: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = useCaseGetOrderBookCase(book)
            _order_bids.postValue(result.payload.bids)
            _order_asks.postValue(result.payload.asks)
        } catch (e: Exception) {
            Log.d("TAG_FRANK", "getAvailableBooks: Error")
        }
    }
}

class DetailViewModelFactory(
    private val useCaseGetAvailableBooksCase: GetDetailBookCase,
    private val useCaseGetOrderBookCase: GetOrderBookCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetDetailBookCase::class.java,
            GetOrderBookCase::class.java
        ).newInstance(useCaseGetAvailableBooksCase, useCaseGetOrderBookCase)
    }
}


