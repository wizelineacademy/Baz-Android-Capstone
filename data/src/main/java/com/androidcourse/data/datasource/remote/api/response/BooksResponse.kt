package com.androidcourse.data.datasource.remote.api.response

data class BooksResponse(
    var success: Boolean? = null,
    var payload: List<BookResponsePayload> = emptyList()
)

data class BookResponsePayload(
    var book: String? = null
)
