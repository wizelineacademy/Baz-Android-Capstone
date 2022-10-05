package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.TickerDomain

data class Ticker(
    val ask: String,
    val bid: String,
    val book: String,
    val change24: String,
    val createdAt: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String
)

fun TickerDomain.toUi() = Ticker(
    ask = ask,
    bid = bid,
    book = book,
    change24 = change24,
    createdAt = createdAt,
    high = high,
    last = last,
    low = low,
    volume = volume
)
