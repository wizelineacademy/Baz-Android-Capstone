package com.example.readbitso.models.bitsoBooks

data class BooksPayload(
    val id:Int =0,
    val book: String,
    val maximum_price: String,
    val minimum_price: String,
)

