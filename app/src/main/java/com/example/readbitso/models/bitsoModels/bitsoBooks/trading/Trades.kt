package com.example.readbitso.models.bitsoModels.bitsoBooks.trading
import com.example.readbitso.models.bitsoModels.bitsoBooks.ErrorX

data class Trades(
    val payload: List<PayloadTrades>,
    val success: Boolean,
    val error: ErrorX
)
