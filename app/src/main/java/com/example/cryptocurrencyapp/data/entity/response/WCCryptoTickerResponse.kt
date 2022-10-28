package com.example.cryptocurrencyapp.data.entity.response

import com.example.cryptocurrencyapp.data.entity.WCCryptoTicker
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoTickerResponse (
    @SerializedName("succes")
    @Expose
    val succes : Boolean = false,

    @SerializedName("payload")
    @Expose
    val payload: WCCryptoTicker? = WCCryptoTicker()
)