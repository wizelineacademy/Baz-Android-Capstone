package com.example.capproject.models.trading

import kotlinx.coroutines.flow.Flow

data class Trades(
    val payload: List<PayloadTrades>,
    val success: Boolean
)