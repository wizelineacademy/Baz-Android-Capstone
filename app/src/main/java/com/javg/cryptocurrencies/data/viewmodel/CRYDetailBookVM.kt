package com.javg.cryptocurrencies.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javg.cryptocurrencies.data.domain.CRYTickerUseCase
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CRYDetailBookVM @Inject constructor(
    private val tickerUseCase: CRYTickerUseCase): ViewModel(){

    private var _tickerBook = MutableLiveData<CRYDetailBook>()
    private var _listAskOrBids = MutableLiveData<MutableList<CRYAskOrBids>>()

    val tickerBook: LiveData<CRYDetailBook>
        get() = _tickerBook

    val listAskOrBids: LiveData<MutableList<CRYAskOrBids>>
        get() = _listAskOrBids

    fun getTicker(book: String){
        viewModelScope.launch {
            val ticker = tickerUseCase.invoke(book)
            _tickerBook.value = ticker
            _listAskOrBids.value = ticker.askList as MutableList<CRYAskOrBids>
        }
    }

    fun setUpdateList(ask: Boolean){
        val listAsk = mutableListOf<CRYAskOrBids>().apply {
            addAll(tickerBook.value?.askList!!)
        }
        val listBids = mutableListOf<CRYAskOrBids>().apply {
            addAll(tickerBook.value?.bidsList!!)
        }
        _listAskOrBids.value = if (ask) listAsk else listBids
    }

}