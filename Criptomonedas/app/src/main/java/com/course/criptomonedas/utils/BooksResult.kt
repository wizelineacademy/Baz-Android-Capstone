package com.course.criptomonedas.utils

sealed class BooksResult<T : Any?> {
    class Loading<T : Any?> : BooksResult<T>()
    data class Success<T : Any?>(val data: T) : BooksResult<T>()
    data class Failure(val exception: Exception) : BooksResult<Nothing>()
}