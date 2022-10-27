package com.example.cryptocurrencyapp.data.entity

import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoOrderBook(
    @SerializedName("book")
    @Expose
    val book: String = "",

    @SerializedName("price")
    @Expose
    val price: String = "",

    @SerializedName("amount")
    @Expose
    val amount: String = ""
){
    fun toOrderDTO(): WCCOrderBookDTO{
        return WCCOrderBookDTO(
            book = book,
            price = price,
            amount = amount,

        )


    }
}