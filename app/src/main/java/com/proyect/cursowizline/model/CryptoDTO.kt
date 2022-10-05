package com.proyect.cursowizline.model

import com.squareup.moshi.Json

class CryptoDTO (
    @field: Json(name = "book")
    val book: String,
    @field: Json(name = "minimum_price")
    val minimum_price:  String,
    @field: Json(name = "maximum_price")
    val maximum_price:  String,
    @field: Json(name = "minimum_value")
    val minimum_value:  String,
    @field: Json(name = "maximum_value")
    val maximum_value:  String,
    @field: Json(name = "tick_size")
    val tick_size:      String,
)

