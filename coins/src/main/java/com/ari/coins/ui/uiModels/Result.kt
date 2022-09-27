package com.ari.coins.ui.uiModels

import com.ari.coins.data.models.ResultData
import com.ari.coins.domain.domainModels.ResultDomain

sealed class Result<T> {
    data class Error<T>(val message: String, val code: Int): Result<T>()
    data class Success<T>(val data: T): Result<T>()
    class Empty<T>: Result<T>()
}

fun <T> ResultData<T>.toUi(): Result<T> = when(this){
    is ResultData.Error -> Result.Error(message, code)
    is ResultData.Success -> Result.Success(data)
}

fun <T> ResultDomain<T>.toUi(): Result<T> = when(this){
    is ResultDomain.Error -> Result.Error(message, code)
    is ResultDomain.Success -> Result.Success(data)
}