package com.jpgl.cryptocurrencies.data.model.response

import com.jpgl.cryptocurrencies.data.model.TickerModel
import com.google.gson.annotations.SerializedName

data class TickerModelResponse(
    var success: Boolean,
    @SerializedName("payload") var dataTicker: TickerModel
)