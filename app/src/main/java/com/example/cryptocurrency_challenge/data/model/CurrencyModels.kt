package com.example.cryptocurrency_challenge.data.model

import com.example.cryptocurrency_challenge.room.TickerEntity

/************** Books MODELS ************************/

data class AvailableBooksResponse(
    val payload: List<Payload> = emptyList(),
    val success: Boolean = false
)

data class Payload(
    val book: String = "",
)

/************** Ticker MODELS ************************/

data class InfoTickerResponse(
    val payload:  PayloadTicker = PayloadTicker(),
    val success: Boolean = false
)

data class PayloadTicker(
    val book: String = "",
    val high: String = "",
    val last: String = "",
    val low: String  = "",
)

fun PayloadTicker.asExternalEntity() = TickerEntity (
    book = book, high = high, last= last, low = low
        )

/************** Order Book Models ************************/

data class OrderBookResponse(
    val payload: PayloadOrderBook = PayloadOrderBook(),
    val success: Boolean = false
)

data class PayloadOrderBook(
    val asks: List<Ask> = emptyList(),
    val bids: List<Bid> = emptyList(),
)

data class Bid(
    val amount  : String = "",
    val book    : String = "",
    val price   : String = ""
)

data class Ask(
    val amount  : String = "",
    val book    : String = "",
    val price   : String = ""
)