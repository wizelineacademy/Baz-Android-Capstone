package com.example.cryptocurrencyapp.data.entity

import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoTicker (
    @SerializedName("book")
    @Expose
    val book: String = "",

    @SerializedName("volume")
    @Expose
    val volume: String  = "",

    @SerializedName("high")
    @Expose
    val high: String = "",

    @SerializedName("last")
    @Expose
    val last: String = "",

    @SerializedName("low")
    @Expose
    val low: String = "",

    @SerializedName("vwap")
    @Expose
    val vwap: String = "",

    @SerializedName("ask")
    @Expose
    val ask: String = "",

    @SerializedName("bid")
    @Expose
    val bid: String = "",

    @SerializedName("created_at")
    @Expose
    val createdAt: String = ""
){
    fun toBitsoTicker(): WCCTickerDTO{
        return WCCTickerDTO(
            book = book,
            high = high,
            low = low,
        )
    }
}