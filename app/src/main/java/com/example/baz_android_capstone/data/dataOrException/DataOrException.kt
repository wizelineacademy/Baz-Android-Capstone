package com.example.baz_android_capstone.data.dataOrException

data class DataOrException<T, Boolean, E : Exception> (
    var data: T? = null,
    var loading: Boolean? = null,
    var exception: E? = null
)