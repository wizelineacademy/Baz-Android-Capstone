package com.example.bazandroidcourse.data.entities

data class BookOrdersModel(
    val asks: List<BookOrderResumeModel> = emptyList(),
    val bids: List<BookOrderResumeModel> = emptyList()
){
    override fun toString(): String {
        return "BookOrderModel(asks=$asks, bids=$bids)"
    }
}
