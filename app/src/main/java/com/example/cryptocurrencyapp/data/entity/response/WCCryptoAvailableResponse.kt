package com.example.cryptocurrencyapp.data.entity.response

import com.example.cryptocurrencyapp.data.entity.WCCryptoAvailable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoAvailableResponse(
    @SerializedName("succes")
    @Expose
    val succes: Boolean  = false,

    @SerializedName("payload")
    @Expose
    val payload: List<WCCryptoAvailable>? = null
)