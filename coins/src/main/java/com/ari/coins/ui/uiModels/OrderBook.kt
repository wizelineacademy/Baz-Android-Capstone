package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.OrderBookDomain

data class OrderBook(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updatedAt: String
)

fun OrderBookDomain.toUi() = OrderBook(
    asks = asks.map { it.toUi() },
    bids = bids.map { it.toUi() },
    sequence = sequence,
    updatedAt = updatedAt
)
