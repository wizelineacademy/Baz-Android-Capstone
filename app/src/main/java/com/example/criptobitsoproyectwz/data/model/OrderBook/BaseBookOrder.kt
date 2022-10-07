package com.example.criptobitsoproyectwz.data.model.OrderBook

import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import com.google.gson.annotations.SerializedName

data class BaseBookOrder(
    @SerializedName("success") val exitoso: Boolean,
    @SerializedName("payload") val payload: PayloadBookOrder
)
