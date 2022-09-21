package com.vero.cursowizelinecriptomonedas.cryptoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.Crypto
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class CryptoListViewModel : ViewModel() {
    //LIVEDATA
    //CryptoList
    private val _cryptoList = MutableLiveData<List<Crypto>>()
    val cryptoList: LiveData<List<Crypto>>
        get() = _cryptoList

    //Status serviceResponse
    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    private val cryptoRepository = CryptoRepository()

    init {
        downloadCrypto()
    }

    private fun downloadCrypto() {
        //Coroutine
        viewModelScope.launch {
            _status.value = ApiResponseStatus.LOADING
            try {
                _cryptoList.value = cryptoRepository.downloadCrypto()
                _status.value = ApiResponseStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = ApiResponseStatus.ERROR
            }
        }
    }
}