package com.javg.cryptocurrencies.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javg.cryptocurrencies.data.domain.CRYBookUseCase
import com.javg.cryptocurrencies.data.model.CRYBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 08/02/2023
 *
 * Contains the necessary functions to obtain the information
 * from the cryptocurrency books
 *
 * @since 1.2
 */
@HiltViewModel
class CRYHomeVM @Inject constructor(
    private val bookUseCase: CRYBookUseCase): ViewModel() {

    private var _listBook = MutableLiveData<List<CRYBook>>()

    val listBook: LiveData<List<CRYBook>>
        get() = _listBook

    /**
     * Is responsible for requesting the list of books from the data layer
     */
    fun getBooks(){
        viewModelScope.launch {
            _listBook.value = bookUseCase.invoke()
        }
    }
}