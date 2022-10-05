package com.ari.coins.data.models

data class OrderBookData(
    val asks: List<AskData>,
    val bids: List<BidData>,
    val sequence: String,
    val updated_at: String
)
