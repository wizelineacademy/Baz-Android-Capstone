package com.wizeline.criptocurrency.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.common.adapters.utilities.formatAsCurrency
import com.wizeline.criptocurrency.domain.model.OpenOrder

data class OpenOrderDto(
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
            price = price?.formatAsCurrency(),
            amount = amount?.toString()
        )
}
