package com.carteagal.baz_android.data.model.availableBook

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvailableBookResponse(
    @Expose
    @SerializedName("book") val book: String,
    @Expose
    @SerializedName("minimum_price") val minimumPrice: String,
    @Expose
    @SerializedName("maximum_price") val maximumPrice: String,
    @Expose
    @SerializedName("minimum_amount") val minimumAmount: String,
    @Expose
    @SerializedName("maximum_amount") val maximumAmount: String,
    @Expose
    @SerializedName("minimum_value") val minimumValue: String,
    @Expose
    @SerializedName("maximum_value") val maximumValue: String,
    @Expose
    @SerializedName("tick_size") val tickSize: String,
)