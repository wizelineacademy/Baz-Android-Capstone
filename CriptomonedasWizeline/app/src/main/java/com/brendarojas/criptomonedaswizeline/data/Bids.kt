package com.brendarojas.criptomonedaswizeline.data

import com.google.gson.annotations.SerializedName

data class Bids (
    var book: String,       //Order book symbol
    val price: String,      //Price per unit of major
    val amount: String,     //Major amount in order
)