package com.example.capproject.models.trading

data class PayloadTrades(
    val amount: String,
    val book: String,
    val created_at: String,
    val maker_side: String,
    val price: String,
    val tid: Int
)