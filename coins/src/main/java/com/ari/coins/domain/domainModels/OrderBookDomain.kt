package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.OrderBookData

data class OrderBookDomain(
    val asks: List<AskDomain>,
    val bids: List<BidDomain>,
    val sequence: String,
    val updatedAt: String
)

fun OrderBookData.toDomain() = OrderBookDomain(
    asks = asks.map { it.toDomain() },
    bids = bids.map { it.toDomain() },
    sequence = sequence,
    updatedAt = updated_at
)
