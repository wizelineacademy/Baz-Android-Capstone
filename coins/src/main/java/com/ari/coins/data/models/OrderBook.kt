package com.ari.coins.data.models

data class OrderBook(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)