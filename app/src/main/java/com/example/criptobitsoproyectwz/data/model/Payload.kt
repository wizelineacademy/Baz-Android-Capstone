package com.example.criptobitsoproyectwz.data.model

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("book") val book: String,
    @SerializedName("minimun_price")val minimun_price: Int,
    @SerializedName("maximun_price")val maximun_price: Int,
    @SerializedName("minimun_amount")val minimun_amount: Double,
    @SerializedName("maximun_amount")val maximun_amount: Int,
    @SerializedName("minimun_value")val minimun_value: Double,
    @SerializedName("maximun_value")val maximun_value: Int,
    @SerializedName("tick_size")val tick_size: Double,
    @SerializedName("default_chart")val default_chart: String,
    @SerializedName("fees")val fees: Fees,

)
