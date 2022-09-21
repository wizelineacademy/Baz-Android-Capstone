package com.vero.cursowizelinecriptomonedas.cryptoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.model.CryptoOrder
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import kotlinx.coroutines.launch

class CryptoOrderListViewModel : ViewModel() {
    private val _cryptoOrderList = MutableLiveData<List<CryptoOrder>>()
    val cryptoOrderList: LiveData<List<CryptoOrder>>
        get() = _cryptoOrderList

    //status -- Status serviceResponse
    private val _status = MutableLiveData<ApiResponseStatus<List<CryptoOrder>>>()
    val status: LiveData<ApiResponseStatus<List<CryptoOrder>>>
        get() = _status

    private val cryptoOrderRepository = CryptoOrderRepository()

    init {
        downloadCryptoOrder()
    }

    private fun downloadCryptoOrder() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(cryptoOrderRepository.downloadCryptoOrder())
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<CryptoOrder>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _cryptoOrderList.value = apiResponseStatus.payload
        }
        _status.value = apiResponseStatus
    }
}