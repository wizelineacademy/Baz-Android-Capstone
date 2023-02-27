package com.example.wizelineandroid.data.remote.model

//List order book
data class OrderBook(
    val payload: AsksBids,
)

data class AsksBids(
    val asks: List<Ask>,
    val bids: List<Bids>,
    val updated_at: String
)

data class Ask(
    val book: String,
    val amount: String,
    val price: String
)
data class Bids(
    val book: String,
    val amount: String,
    val price: String
)