package com.ari.coins.data.models

sealed class ResultData<T> {
    data class Error<T>(val message: String, val code: Int) : ResultData<T>()
    data class Success<T>(val data: T) : ResultData<T>()
}
