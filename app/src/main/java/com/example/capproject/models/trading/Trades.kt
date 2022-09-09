package com.example.capproject.models.trading

data class Trades(
    val payload: List<PayloadTrades>,
    val success: Boolean
)