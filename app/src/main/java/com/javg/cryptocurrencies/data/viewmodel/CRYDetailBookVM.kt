package com.javg.cryptocurrencies.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javg.cryptocurrencies.data.domain.CRYGetListBookWithTickerUseCase
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CRYDetailBookVM @Inject constructor(
    private val tickerUseCase: CRYGetListBookWithTickerUseCase
): ViewModel(){

    private var _tickerBook = MutableLiveData<CRYDetailBook>()
    private var _listAskOrBids = MutableLiveData<List<CRYAskOrBids>>()

    val tickerBook: LiveData<CRYDetailBook>
        get() = _tickerBook

    val listAskOrBids: LiveData<List<CRYAskOrBids>>
        get() = _listAskOrBids

    fun getTicker(book: String){
        viewModelScope.launch {
            val ticker = tickerUseCase.invoke(book)
            _tickerBook.value = ticker
            _listAskOrBids.value = ticker.askList as MutableList<CRYAskOrBids>
        }
    }

    fun sendListUpdate(listUpdate: List<CRYAskOrBids>){
        _listAskOrBids.value = listUpdate
    }

}