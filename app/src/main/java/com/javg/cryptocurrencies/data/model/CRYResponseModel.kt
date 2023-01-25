package com.javg.cryptocurrencies.data.model

import com.google.gson.annotations.SerializedName

data class CRYBaseResponseModel(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("payload")
    var payload: List<CRYBook>
)

data class CRYBook(
    @SerializedName("book")
    var book: String,
    @SerializedName("minimum_price")
    var minimumPrice: String,
    @SerializedName("maximum_price")
    var maximumPrice: String,
    @SerializedName("minimum_amount")
    var minimumAmount: String,
    @SerializedName("maximum_amount")
    var maximumAmount: String,
    @SerializedName("minimum_value")
    var minimumValue: String,
    @SerializedName("maximum_value")
    var maximumValue: String,
    @SerializedName("tick_size")
    var tickSize: String,
    @SerializedName("default_chart")
    var defaultChart: String
)