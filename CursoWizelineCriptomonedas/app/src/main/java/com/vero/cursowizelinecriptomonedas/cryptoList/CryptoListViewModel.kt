package com.vero.cursowizelinecriptomonedas.cryptoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.model.Crypto
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import kotlinx.coroutines.launch

class CryptoListViewModel : ViewModel() {
    //LIVEDATA
    //CryptoList
    private val _cryptoList = MutableLiveData<List<Crypto>>()
    val cryptoList: LiveData<List<Crypto>>
        get() = _cryptoList

    //Status serviceResponse
    private val _status = MutableLiveData<ApiResponseStatus<List<Crypto>>>()
    val status: LiveData<ApiResponseStatus<List<Crypto>>>
        get() = _status

    private val cryptoRepository = CryptoRepository()

    init {
        downloadCrypto()
    }

    private fun downloadCrypto() {
        //Coroutine
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(cryptoRepository.downloadCrypto())
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<Crypto>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _cryptoList.value = apiResponseStatus.payload
        }
        _status.value = apiResponseStatus
    }
}