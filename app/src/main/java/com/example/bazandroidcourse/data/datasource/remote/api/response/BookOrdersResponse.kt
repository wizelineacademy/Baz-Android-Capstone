package com.example.bazandroidcourse.data.datasource.remote.api.response

data class BookOrdersResponse(
    var success: Boolean? = null,
    var payload: OrderPayloadResponse
)

data class OrderPayloadResponse(
    var asks: List<OrderResume>? = null,
    var bids: List<OrderResume>? = null
)

data class OrderResume(
    var book: String?,
    var price: String?,
    var amount: String?
)
