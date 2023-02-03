package com.example.cryptocurrencyapp.data.remote.entity

import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoTicker(
    @SerializedName("book")
    @Expose
    val coin: String = "",

    @SerializedName("volume")
    @Expose
    val volumeCoin: String = "",

    @SerializedName("high")
    @Expose
    val highCoin: String = "",

    @SerializedName("last")
    @Expose
    val lastCoin: String = "",

    @SerializedName("low")
    @Expose
    val lowCoin: String = "",

    @SerializedName("vwap")
    @Expose
    val vwapCoin: String = "",

    @SerializedName("ask")
    @Expose
    val askCoin: String = "",

    @SerializedName("bid")
    @Expose
    val bidCoin: String = "",

    @SerializedName("created_at")
    @Expose
    val createdAt: String = ""
)

fun WCCryptoTicker.toBitsoTicker(): WCCTickerDTO {
    return WCCTickerDTO(
        book = this.coin,
        high = highCoin,
        low = lowCoin,
    )
}