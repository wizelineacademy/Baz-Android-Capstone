package com.example.bazandroidcourse.data.model

data class BookModel(
    override val book: String ="btc_btc",
    val name: String = "name"
): Book(book = book)
