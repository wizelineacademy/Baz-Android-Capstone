package com.example.bazandroidcourse.data.datasource.remote.api.response

data class BooksResponse(
    var success: Boolean? = null,
    var payload: List<BookResponse> = emptyList()
)

data class BookResponse (
    var book: String? = null
)