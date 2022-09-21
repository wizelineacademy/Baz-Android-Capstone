package com.brendarojas.criptomonedaswizeline.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brendarojas.criptomonedaswizeline.adapter.AvailableBooksAdapter
import kotlinx.coroutines.launch

class CryptoViewModel() : ViewModel() {
    var adapterAvailableBooks = MutableLiveData<AvailableBooksAdapter>()

    fun getListAvailableBooks(){
        viewModelScope.launch {

        }
    }

}