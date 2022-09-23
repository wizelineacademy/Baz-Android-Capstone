package com.proyect.cursowizline.view.cryptoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyect.cursowizline.model.CryptoOrder
import com.proyect.cursowizline.model.ResponseStatus
import kotlinx.coroutines.launch

class CryptoDetailListViewModel : ViewModel() {
    private val _cryptoOrderList = MutableLiveData<List<CryptoOrder>>()
    val cryptoOrderList: LiveData<List<CryptoOrder>>
        get() = _cryptoOrderList

    private val _status = MutableLiveData<ResponseStatus<List<CryptoOrder>>>()
    val status: LiveData<ResponseStatus<List<CryptoOrder>>>
        get() = _status

    private val cryptoOrderRepository = CryptoDetailRepository()

    init {
        downloadCryptoOrder()
    }

    private fun downloadCryptoOrder() {
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            handleResponseStatus(cryptoOrderRepository.downloadCryptoOrder())
        }
    }

    private fun handleResponseStatus(responseStatus: ResponseStatus<List<CryptoOrder>>) {
        if (responseStatus is ResponseStatus.Success) {
            _cryptoOrderList.value = responseStatus.payload
        }
        _status.value = responseStatus
    }
}