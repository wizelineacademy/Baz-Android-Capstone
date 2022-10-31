package com.example.cryptocurrencyapp.data.entity

import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class  WCCryptoAvailable (
    @SerializedName("book")
    @Expose
    val book: String  = "",

    @SerializedName("minimum_amount")
    @Expose
    val minAmount: String = "",

    @SerializedName("maximum_amount")
    @Expose
    val maxAmounut: String = "",

    @SerializedName("minimum_price")
    @Expose
    val minPrice: String = "",

    @SerializedName("maximum_price")
    @Expose
    val maxPrice: String = "",

    @SerializedName("minimum_value")
    @Expose
    val minValue: String = "",

    @SerializedName("maximum_value")
    @Expose
    val maxValue: String = ""
){
    fun toBook(): WCCryptoBookDTO{
        return WCCryptoBookDTO(
            book = book,
            minPrice = minAmount,
            maxPrice = maxAmounut
        )
    }
}