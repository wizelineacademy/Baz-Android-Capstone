package com.example.cryptocurrency_challenge.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


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
    @SerializedName("high")     val high: String,
    @SerializedName("last")     val last: String,
    @SerializedName("low")      val low: String,
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


/************** MOSHI MODELS ************************/

@JsonClass(generateAdapter = true)
data class Available_books_response_moshi(
    @field:Json(name= "payload") val payload: List<PayloadMoshi>,
    @field:Json(name= "success") val success: Boolean
)

@JsonClass(generateAdapter = true)
data class PayloadMoshi(
    @field:Json(name= "book") val book: String,
)

/************** MOSHI MODELS ************************/