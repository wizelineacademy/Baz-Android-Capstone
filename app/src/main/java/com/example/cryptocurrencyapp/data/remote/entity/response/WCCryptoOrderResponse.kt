package com.example.cryptocurrencyapp.data.remote.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoOrderResponse (
    @SerializedName("succes")
    @Expose
    val succes : Boolean = false,

    @SerializedName("payload")
    @Expose
    val orderCoin: WCCOrder?= null
)