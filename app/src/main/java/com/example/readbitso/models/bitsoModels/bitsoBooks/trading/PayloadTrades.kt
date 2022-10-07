package com.example.readbitso.models.bitsoModels.bitsoBooks.trading

import com.google.gson.annotations.SerializedName

data class PayloadTrades(
    @SerializedName("amount") val amount: String,
    @SerializedName("book") val book: String,
    @SerializedName("maker_side") val makerSide: String,
    @SerializedName("price") val price: String
)
