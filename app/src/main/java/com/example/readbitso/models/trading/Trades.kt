package com.example.readbitso.models.trading
import com.example.readbitso.models.ErrorX


data class Trades(
    val payload: List<PayloadTrades>,
    val success: Boolean,
    val error: ErrorX
)