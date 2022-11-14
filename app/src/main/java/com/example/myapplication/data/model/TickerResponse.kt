package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

data class TickerPayloadResponse(

    @SerializedName("success") val success: Boolean? = false,
    @SerializedName("payload") val payload : List<TickerResponse>? = null,
)

data class TickerResponse(

    @SerializedName("book") var book: String? = "",
    @SerializedName("volume") var volume: Double? = 0.0,
    @SerializedName("high") var high: Double? = 0.0,
    @SerializedName("last")  var last: Double? = 0.0,
    @SerializedName("low")  var low: Double? = 0.0,
    @SerializedName("vwap")  var vwap: Double? = 0.0,
    @SerializedName("ask")  var ask: Double? = 0.0,
    @SerializedName("bid")  var bid: Double? = 0.0

)