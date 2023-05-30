package com.andcourse.domain.model

data class BookDetailModel(
    override val book: String = "",
    val volume: String = "",
    val high: String = "",
    val last: String = "",
    val low: String = ""
): Book(book = book)
