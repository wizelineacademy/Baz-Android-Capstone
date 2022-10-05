package com.ari.coins.ui.uiModels

sealed class Result<T> {
    data class Error<T>(val message: String, val code: Int) : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    class Empty<T> : Result<T>()
}
