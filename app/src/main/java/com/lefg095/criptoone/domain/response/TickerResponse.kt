package com.lefg095.criptoone.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lefg095.criptoone.domain.Ticker

class TickerResponse(
    @Expose
    @SerializedName("success")
    var success: String = "",
    @Expose
    @SerializedName("payload")
    var payload: Ticker? = null
)