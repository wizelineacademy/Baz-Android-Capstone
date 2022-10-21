package com.example.bazandroidcourse.data.datasource.remote.api.response

data class BookOrdersResponse(
    var success: Boolean? = null,
    var payload: OrderPayload
)

data class OrderPayload(
    var asks: List<BookResume>? = null,
    var bids: List<BookResume>? = null
)

data class BookResume(
    var book: String?,
    var price: String?,
    var amount: String?
)
