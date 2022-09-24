package com.example.readbitso.models.bitsoModels.bitsoBooks.trading

data class PayloadTrades(
    val amount: String,
    val book: String,
    val maker_side: String,
    val price: String
)



