package com.example.cryptocurrencyapp.data.remote.entity.response

import com.example.cryptocurrencyapp.data.remote.entity.WCCryptoTicker
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoTickerResponse (
    @SerializedName("succes")
    @Expose
    val succes : Boolean = false,

    @SerializedName("payload")
    @Expose
    val tickerCoin: WCCryptoTicker? = null
)