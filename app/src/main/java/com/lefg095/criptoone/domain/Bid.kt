package com.lefg095.criptoone.domain


import com.google.gson.annotations.SerializedName

data class Bid(
    @SerializedName("amount")
    var amount: String,
    @SerializedName("book")
    var book: String,
    @SerializedName("price")
    var price: String
)