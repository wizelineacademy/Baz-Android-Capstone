package com.lefg095.criptoone.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticker(
    @SerializedName("ask")
    var ask: String = "",
    @SerializedName("bid")
    var bid: String,
    @SerializedName("book")
    var book: String,
    @SerializedName("created_at")
    var createdAt: String = "",
    @SerializedName("high")
    var high: String,
    @SerializedName("last")
    var last: String,
    @SerializedName("low")
    var low: String,
    @SerializedName("volume")
    var volume: String,
    @SerializedName("vwap")
    var vwap: String
): Parcelable