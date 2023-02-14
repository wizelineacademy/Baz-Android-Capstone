package com.javg.cryptocurrencies.view.viewmodel

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

/**
 * @author Juan Vera Gomez
 *
 * Contains the functionality to consult the specific information of each book
 *
 * @param tickerUseCase It is the case of use who manages
 * the obtaining of information in the data layer
 *
 * @since 2.0
 */
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

    /**
     * Consult the price information and ask list and bids of a specific book
     *
     * @param book is the name of the book to consult its specific information
     */
    fun getTicker(book: String){
        viewModelScope.launch {
            val ticker = tickerUseCase.invoke(book)
            _tickerBook.value = ticker
            ticker.askList?.let {
                _listAskOrBids.value = it as MutableList<CRYAskOrBids>
            }
        }
    }

    /**
     * Updates a list depending on the selection at view level
     *
     * @param listUpdate is the list that will update the view
     */
    fun sendListUpdate(listUpdate: List<CRYAskOrBids>){
        _listAskOrBids.value = listUpdate
    }

}