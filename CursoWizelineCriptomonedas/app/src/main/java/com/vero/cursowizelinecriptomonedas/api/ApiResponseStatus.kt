package com.vero.cursowizelinecriptomonedas.api

import com.vero.cursowizelinecriptomonedas.Crypto

sealed class ApiResponseStatus {
    class Success(val cryptoList: List<Crypto>) : ApiResponseStatus()
    object Loading : ApiResponseStatus()
    class Error(val messageId: Int) : ApiResponseStatus()
}