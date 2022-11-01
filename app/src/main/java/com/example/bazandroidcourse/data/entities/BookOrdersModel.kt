package com.example.bazandroidcourse.data.entities

data class BookOrdersModel(
    val asks: List<BookOrderResumeModel>,
    val bids: List<BookOrderResumeModel>
){
    override fun toString(): String {
        return "BookOrderModel(asks=$asks, bids=$bids)"
    }
}
