package com.example.cryptocurrencyapp.data.entity.response

import com.example.cryptocurrencyapp.data.entity.WCCryptoOrderBook
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.utils.CryptoConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCOrder(
    @SerializedName("asks")
    @Expose
    val ask: List<WCCryptoOrderBook>,

    @SerializedName("bids")
    @Expose
    val bids: List<WCCryptoOrderBook>,

    @SerializedName("updated_at")
    @Expose
    val updatedAt: String = "",

    @SerializedName("sequence")
    @Expose
    val sequence: String = ""
){
    fun toOrder(): WCCOrdeRDTO{
        return WCCOrdeRDTO(
            ask = ask.map { ask ->
                ask.toOrderDTO(CryptoConstants.ASK)
            }.toMutableList(),
            bids = bids.map { bid ->
                bid.toOrderDTO(CryptoConstants.BID)
            }.toMutableList()
        )
    }
}