package com.carteagal.baz_android.data.model.tickerResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @Expose
    @SerializedName("book") val book: String? = "" ,
    @Expose
    @SerializedName("volume") val volume: String? = "",
    @Expose
    @SerializedName("high") val high: String? = "",
    @Expose
    @SerializedName("last") val last: String? = "",
    @Expose
    @SerializedName("low") val low: String? = "",
    @Expose
    @SerializedName("vwap") val vwap: String? = "",
    @Expose
    @SerializedName("ask") val ask: String? = "",
    @Expose
    @SerializedName("bid") val bid: String? = "",
    @Expose
    @SerializedName("created_at") val createdAt: String? = ""
)