package com.proyect.cursowizline.model

import com.squareup.moshi.Json

class CryptoOrderDTO (
    @field: Json(name = "book")
    val book:   String,
    @field: Json(name = "price")
    val price:  String,
    @field: Json(name = "amount")
    val amount: String,
)