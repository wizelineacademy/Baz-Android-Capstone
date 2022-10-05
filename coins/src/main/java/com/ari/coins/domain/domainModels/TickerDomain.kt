package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.TickerData

data class TickerDomain(
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

fun TickerData.toDomain() = TickerDomain(
    ask = ask,
    bid = bid,
    book = book,
    change24 = change_24,
    createdAt = created_at,
    high = high,
    last = last,
    low = low,
    volume = volume
)
