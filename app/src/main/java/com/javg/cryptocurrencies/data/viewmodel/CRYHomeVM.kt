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

@HiltViewModel
class CRYHomeVM @Inject constructor(
    private val bookUseCase: CRYBookUseCase): ViewModel() {

    private var _listBook = MutableLiveData<MutableList<CRYBook>>()

    val listBook: LiveData<MutableList<CRYBook>>
        get() = _listBook

    fun getBooks(){
        viewModelScope.launch {
            _listBook.value = bookUseCase.invoke()
        }
    }
}