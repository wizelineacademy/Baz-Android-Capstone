package com.ari.coins.ui.models

import com.ari.coins.data.models.Result

sealed class ResultUi<T> {
    data class Error<T>(val message: String, val code: Int): ResultUi<T>()
    data class Success<T>(val data: T): ResultUi<T>()
    class Empty<T>: ResultUi<T>()
}

fun <T> Result<T>.toUi(): ResultUi<T> = when(this){
    is Result.Error -> ResultUi.Error(message, code)
    is Result.Success -> ResultUi.Success(data)
}