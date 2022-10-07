package com.example.cryptocurrency_challenge.data.model

/************** Books MODELS ************************/

data class Available_books_response(
    val payload: List<Payload>,
    val success: Boolean
)

data class Payload(
    val book: String,
)

/************** Ticker MODELS ************************/

data class InfoTickerResponse(
    val payload:  Payload_Ticker,
    val success: Boolean
)

data class Payload_Ticker(
    val high: String,
    val last: String,
    val low: String,
)

/************** Order Book Models ************************/

data class OrderBookResponse(
    val payload: PayloadOrderBook,
    val success: Boolean
)

data class PayloadOrderBook(
    val asks: List<Ask>,
    val bids: List<Bid>,
)

data class Bid(
    val amount: String,
    val book: String,
    val price: String
)

data class Ask(
    val amount: String,
    val book: String,
    val price: String
)