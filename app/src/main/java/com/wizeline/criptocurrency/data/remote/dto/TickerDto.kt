package com.wizeline.criptocurrency.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.domain.model.Ticker
import java.io.Serializable
import java.util.*


data class TickerDto(
    @SerializedName("book")
    @Expose
    var book: String? = null,
    @SerializedName("volume")
    @Expose
    var volume: String? = null,
    @SerializedName("high")
    @Expose
    var high: String? = null,
    @SerializedName("last")
    @Expose
    var last: String? = null,
    @SerializedName("low")
    @Expose
    var low: String? = null,
    @SerializedName("vwap")
    @Expose
    var vwap: String? = null,
    @SerializedName("ask")
    @Expose
    var ask: String? = null,
    @SerializedName("bid")
    @Expose
    var bid: String? = null,
    @SerializedName("created_at")
    @Expose
    var created_at: Date? = null,
    @SerializedName("change_24")
    @Expose
    var change_24: String? = null,
    @SerializedName("rolling_average_change")
    @Expose
    var rolling_average_change: Any? = null,
): Serializable {
    fun toTicker(): Ticker =
        Ticker(
            book = book,
            volume = volume,
            high = high,
            last = last,
            low = low,
            vwap = vwap,
            ask = ask,
            bid = bid,
            created_at = created_at,
            change_24 = change_24,
            rolling_average_change = rolling_average_change
        )
}