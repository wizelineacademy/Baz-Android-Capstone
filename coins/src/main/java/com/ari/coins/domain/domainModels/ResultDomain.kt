package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.ResultData

sealed class ResultDomain<T> {
    data class Error<T>(val message: String, val code: Int): ResultDomain<T>()
    data class Success<T>(val data: T): ResultDomain<T>()
}

fun <T> ResultData<T>.toDomain(): ResultDomain<T> = when(this){
    is ResultData.Error -> ResultDomain.Error(message, code)
    is ResultData.Success -> ResultDomain.Success(data)
}