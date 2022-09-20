package com.example.criptobitsoproyectwz.data.model.OrderBook

import com.google.gson.annotations.SerializedName

data class Asks(
    @SerializedName("book") val book: String,
    @SerializedName("price") val price: Int,
    @SerializedName("amount") val amount: Double
)