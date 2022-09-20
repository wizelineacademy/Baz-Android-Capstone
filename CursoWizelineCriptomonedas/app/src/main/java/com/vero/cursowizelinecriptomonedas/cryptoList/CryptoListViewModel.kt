package com.vero.cursowizelinecriptomonedas.cryptoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.Crypto
import kotlinx.coroutines.launch

class CryptoListViewModel : ViewModel() {
    //LIVEDATA
    private val _cryptoList = MutableLiveData<List<Crypto>>()
    val cryptoList: LiveData<List<Crypto>>
        get() = _cryptoList

    private val cryptoRepository = CryptoRepository()

    init {
        downloadCrypto()
    }

    private fun downloadCrypto() {
        //Coroutine
        viewModelScope.launch {
            _cryptoList.value = cryptoRepository.downloadCrypto()
        }
    }
}