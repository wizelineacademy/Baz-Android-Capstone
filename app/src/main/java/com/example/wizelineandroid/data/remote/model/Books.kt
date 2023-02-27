package com.example.wizelineandroid.data.remote.model

//List available books
data class Books(
    val payload: List<ModelBook>,
    val success: Boolean
)
data class ModelBook(
    val book: String,
    val minimum_price: String,
    val maximum_price: String
)


