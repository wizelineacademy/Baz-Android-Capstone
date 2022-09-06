package com.example.criptobitsoproyectwz.data.model

import com.google.gson.annotations.SerializedName

data class Structure(
    @SerializedName("volume") val volume: Double,
    @SerializedName("maker") val maker: Double,
    @SerializedName("taker") val tajer: Double
)
