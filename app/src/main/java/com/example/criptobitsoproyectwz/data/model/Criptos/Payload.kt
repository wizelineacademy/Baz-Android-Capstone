package com.example.criptobitsoproyectwz.data.model.Criptos

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("book") val book: String,
    @SerializedName("maximum_price") val maximo: Double
)
