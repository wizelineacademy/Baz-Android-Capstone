package com.example.capstoneproject.data.model.orderBook

data class OrderBookModel(
    val asks: List<AskModel> = emptyList(),
    val bids: List<BidModel> = emptyList()
)