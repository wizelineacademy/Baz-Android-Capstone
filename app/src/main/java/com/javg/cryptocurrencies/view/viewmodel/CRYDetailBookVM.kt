package com.javg.cryptocurrencies.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.domain.CRYGetListBookWithTickerUseCase
import com.javg.cryptocurrencies.data.model.CRYAskOrBids
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.utils.CRYUtils
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
    application: Application,
    private val tickerUseCase: CRYGetListBookWithTickerUseCase,
) : AndroidViewModel(application) {

    private var _tickerBook = MutableLiveData<CRYDetailBook>()
    private var _listAskOrBids = MutableLiveData<List<CRYAskOrBids>>()
    private var _emptyData = MutableLiveData<Boolean>()
    private var _updateTime = MutableLiveData<String>()

    val tickerBook: LiveData<CRYDetailBook>
        get() = _tickerBook

    val listAskOrBids: LiveData<List<CRYAskOrBids>>
        get() = _listAskOrBids

    val emptyData: LiveData<Boolean>
        get() = _emptyData

    val updateTime: LiveData<String>
        get() = _updateTime

    init {
        _updateTime.value = getTimeUpdate()
    }

    /**
     * Consult the price information and ask list and bids of a specific book
     *
     * @param book is the name of the book to consult its specific information
     */
    fun getTicker(book: String) {
        viewModelScope.launch {
            val ticker = tickerUseCase.invoke(book)
            ticker?.let {
                _tickerBook.value = it
                it.askList?.let { list ->
                    _listAskOrBids.value = list as MutableList<CRYAskOrBids>
                    _updateTime.value = getTimeUpdate()
                }
            } ?: run {
                _emptyData.value = true
            }
        }
    }

    /**
     * Updates a list depending on the selection at view level
     *
     * @param listUpdate is the list that will update the view
     */
    fun sendListUpdate(listUpdate: List<CRYAskOrBids>) {
        _listAskOrBids.value = listUpdate
    }

    /**
     * Returns a composite legend with the updated time of the remote service query
     */
    private fun getTimeUpdate(): String = String.format(
        getApplication<Application>().applicationContext.getString(
            R.string.cry_update_day,
        ),
        CRYUtils.getSaveTime(getApplication<Application>().applicationContext),
    )
}
