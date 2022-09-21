package com.example.cryptocurrency_challenge.model

import com.google.gson.annotations.SerializedName


data class Available_books_response(
    @SerializedName("payload") val payload: List<Payload>,
    @SerializedName("success") val success: Boolean
)

data class Payload(
    @SerializedName("book")             val book: String,
    @SerializedName("maximum_amount")   val maximum_amount: String,
    @SerializedName("maximum_price")    val maximum_price: String,
    @SerializedName("maximum_value")    val maximum_value: String,
    @SerializedName("minimum_amount")   val minimum_amount: String,
    @SerializedName("minimum_price")    val minimum_price: String,
    @SerializedName("minimum_value")    val minimum_value: String
)

data class InfoTickerResponse(
    @SerializedName("payload")   val payload:  Payload_Ticker,
    @SerializedName("success")   val success: Boolean
)

data class Payload_Ticker(
    val ask: String,
    val bid: String,
    val book: String,
    val created_at: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String,
    val vwap: String
)

data class PayLoadModel(
    val book: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)