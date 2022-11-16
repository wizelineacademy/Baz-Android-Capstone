package com.example.criptobitsoproyectwz.data.model.Ticket

import com.google.gson.annotations.SerializedName

data class PayloadCripto(
    @SerializedName("book")val book : String,
    @SerializedName("high")val high : Double,
    @SerializedName("low")val low : Double,
    @SerializedName("last")val last : Double
)
