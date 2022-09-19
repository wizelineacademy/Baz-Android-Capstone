package com.example.criptobitsoproyectwz.data.model.Ticket

import com.google.gson.annotations.SerializedName

data class PayloadCripto(
    @SerializedName("book")val book : String,
    @SerializedName("ask")val ask : Int,
    @SerializedName("bid")val bid : Int
)
