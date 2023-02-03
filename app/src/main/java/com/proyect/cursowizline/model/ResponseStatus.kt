package com.proyect.cursowizline.model

open class ResponseStatus <T> {

    class Success<T>(val payload: T) : ResponseStatus<T>()
    class Loading<T> : ResponseStatus<T>()
    class Error<T>(val messageId: Int) : ResponseStatus<T>()
}