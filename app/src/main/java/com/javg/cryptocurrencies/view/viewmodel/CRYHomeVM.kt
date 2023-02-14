package com.javg.cryptocurrencies.view.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.domain.CRYBookUseCase
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.utils.CRYUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 10/02/2023
 *
 * Contains the necessary functions to obtain the information
 * from the cryptocurrency books
 *
 * @since 1.2
 */
@HiltViewModel
class CRYHomeVM @Inject constructor(application: Application,
    private val bookUseCase: CRYBookUseCase): AndroidViewModel(application) {

    private var _listBook = MutableLiveData<List<CRYBook>>()
    private var _updateTime = MutableLiveData<String>()

    val listBook: LiveData<List<CRYBook>>
        get() = _listBook

    val updateTime: LiveData<String>
        get() = _updateTime

    init {
        _updateTime.value = getTimeUpdate()
    }

    /**
     * It is responsible for requesting the list of books from the data
     * layer which can be called with user interaction.
     *
     * @param onRefresh flag that will indicate to the data layer
     * if it consults the remote information again
     *
     */
    fun getBooks(onRefresh: Boolean = false){
        viewModelScope.launch {
            _listBook.value = bookUseCase.invoke(onRefresh)
            if (onRefresh && _listBook.value?.isNotEmpty()!!)
                _updateTime.value = getTimeUpdate()
            else
                _updateTime.value = getTimeUpdate()
        }
    }

    /**
     * Returns a composite legend with the updated time of the remote service query
     */
    private fun getTimeUpdate(): String = String.format(getApplication<Application>().applicationContext.getString(R.string.cry_update_day), CRYUtils.getSaveTime(getApplication<Application>().applicationContext))


}