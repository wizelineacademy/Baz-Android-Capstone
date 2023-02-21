package com.example.baz_android_capstone.util // ktlint-disable package-name

sealed class Resource<T> (
    val data: T? = null,
    val throwable: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(data: T? = null, throwable: Throwable) : Resource<T>(data = data, throwable = throwable)
}
