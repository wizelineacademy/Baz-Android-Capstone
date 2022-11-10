package com.wizeline.criptocurrency.data.remote.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.data.remote.dto.AvailableBookDto
import com.wizeline.criptocurrency.data.remote.dto.OrderBookDto
import com.wizeline.criptocurrency.domain.model.OpenOrder
import com.wizeline.criptocurrency.domain.model.OrderBook
import java.io.Serializable
import java.util.*

data class OrderBookResponse (
    @SerializedName("success")
    @Expose
    var success: Boolean? = null,
    @SerializedName("payload")
    @Expose
    var payload: OrderBookDto? = null
) {

}