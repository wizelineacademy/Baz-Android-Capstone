package com.wizeline.criptocurrency.data.remote.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.data.remote.dto.AvailableBookDto
import com.wizeline.criptocurrency.domain.model.OpenOrder
import java.io.Serializable

data class OpenOrderResponse (
    @SerializedName("book")
    @Expose
    var book: String? = null,
    @SerializedName("price")
    @Expose
    var price: Double? = null,
    @SerializedName("amount")
    @Expose
    var amount: Double? = null
) {
    fun toOpenOrder(): OpenOrder =
        OpenOrder(
            book = book,
            price = price?.toString(),
            amount = amount?.toString()
        )
}