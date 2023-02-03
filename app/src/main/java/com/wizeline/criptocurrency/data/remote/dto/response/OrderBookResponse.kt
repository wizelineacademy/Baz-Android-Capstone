package com.wizeline.criptocurrency.data.remote.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.data.remote.dto.AvailableBookDto
import com.wizeline.criptocurrency.domain.model.OpenOrder
import com.wizeline.criptocurrency.domain.model.OrderBook
import java.io.Serializable
import java.util.*

data class OrderBookResponse (
    @SerializedName("success")
    @Expose
    var success: Boolean? = null,
    @SerializedName("asks")
    @Expose
    var asks: List<OpenOrderResponse>? = null,
    @SerializedName("bids")
    @Expose
    var bids: List<OpenOrderResponse>? = null,
    @SerializedName("updated_at")
    @Expose
    var updated_at: Date? = null,
    @SerializedName("sequence")
    @Expose
    var sequence: String? = null
) {
    fun toOrderBook(book: String): OrderBook =
        OrderBook(
            book = book,
            asks = asks.toOrderBookList,
            bids = bids.toOrderBookList
        )

    private val List<OpenOrderResponse>?.toOrderBookList: List<OpenOrder>
        get() = mutableListOf<OpenOrder>()
            .apply {
                this@toOrderBookList?.forEach {
                    this.add(it.toOpenOrder())
                }
            }
}