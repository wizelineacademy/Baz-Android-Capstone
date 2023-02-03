package com.vero.cursowizelinecriptomonedas.api

sealed class ApiResponseStatus<T> {
    class Success<T>(val payload: T) : ApiResponseStatus<T>()
    class Loading<T> : ApiResponseStatus<T>()
    class Error<T>(val messageId: Int) : ApiResponseStatus<T>()
}