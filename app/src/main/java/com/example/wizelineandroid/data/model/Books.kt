package com.example.wizelineandroid.data.model

//List available books
data class Books(
    val payload: List<ModelBook>,
    val success: Boolean
)
data class ModelBook(
    val book: String,
    val minimum_price: String,
    val maximum_price: String
)

//Get ticker
data class GetTickers(
    val payload: GetTicker
)

data class GetTicker(
    val high: String,
    val last: String,
    val low: String,
)

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