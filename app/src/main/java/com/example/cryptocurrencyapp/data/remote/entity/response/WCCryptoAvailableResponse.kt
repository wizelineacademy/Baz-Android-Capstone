package com.example.cryptocurrencyapp.data.remote.entity.response

import com.example.cryptocurrencyapp.data.remote.entity.WCCryptoAvailable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoAvailableResponse(
    @SerializedName("success")
    @Expose
    val success: Boolean  = false,

    @SerializedName("payload")
    @Expose
    val coins: List<WCCryptoAvailable>? = null
)