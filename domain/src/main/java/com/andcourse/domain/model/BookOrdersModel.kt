package com.andcourse.domain.model

data class BookOrdersModel(
    val asks: List<BookOrderResumeModel> = emptyList(),
    val bids: List<BookOrderResumeModel> = emptyList()
) {
    override fun toString(): String {
        return "BookOrderModel(asks=$asks, bids=$bids)"
    }
}
