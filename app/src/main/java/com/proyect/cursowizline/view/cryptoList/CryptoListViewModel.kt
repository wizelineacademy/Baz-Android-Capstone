package com.proyect.cursowizline.view.cryptoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyect.cursowizline.model.Crypto
import com.proyect.cursowizline.model.ResponseStatus
import kotlinx.coroutines.launch

class CryptoListViewModel : ViewModel() {

    private val _cryptoList = MutableLiveData<List<Crypto>>()
    val cryptoList: LiveData<List<Crypto>>
        get() = _cryptoList


    private val _status = MutableLiveData<ResponseStatus<List<Crypto>>>()
    val status: LiveData<ResponseStatus<List<Crypto>>>
        get() = _status

    private val cryptoRepository = CryptoRepository()

    init {
        downloadCrypto()
    }

    private fun downloadCrypto() {
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            handleResponseStatus(cryptoRepository.downloadCrypto())
        }
    }

    private fun handleResponseStatus(responseStatus: ResponseStatus<List<Crypto>>) {
        if (responseStatus is ResponseStatus.Success) {
            _cryptoList.value = responseStatus.payload
        }
        _status.value = responseStatus
    }
}