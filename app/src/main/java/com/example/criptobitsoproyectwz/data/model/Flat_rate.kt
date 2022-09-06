package com.example.criptobitsoproyectwz.data.model

import com.google.gson.annotations.SerializedName

data class Flat_rate(
    @SerializedName("maker") val maker: Double,
    @SerializedName("taker") val tajer: Double
)
