package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

data class OrderBooksModel(
    @SerializedName("success") val success: Boolean? = false,
    @SerializedName("payload") val payload : List<Asks>? = null,
)

data class Asks(
    @SerializedName("asks") val asks: List<OrderBookResponse>? = null,
    @SerializedName("binds") val binds: List<OrderBookResponse>? = null
)

data class OrderBookResponse(
    @SerializedName("book") var book: String? = "",
    @SerializedName("price") var price: Double? = 0.0,
    @SerializedName("amount") var maximumAmount: Double? = 0.0
)