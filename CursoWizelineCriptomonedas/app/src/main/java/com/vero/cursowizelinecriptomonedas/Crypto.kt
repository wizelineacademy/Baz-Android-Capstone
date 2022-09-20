package com.vero.cursowizelinecriptomonedas

import com.squareup.moshi.Json

//Cryptocurrency
class Crypto (
    @field: Json(name = "book")
    val book:           String,

    @field: Json(name = "minimum_price")
    val minimum_price:  Double,

    @field: Json(name = "maximum_price")
    val maximum_price:  Double,

    @field: Json(name = "minimum_amount")
    val minimum_amount: Double,

    @field: Json(name = "maximum_amount")
    val maximum_amount: Double,

    @field: Json(name = "minimum_value")
    val minimum_value:  Double,

    @field: Json(name = "maximum_value")
    val maximum_value:  Double,

    @field: Json(name = "tick_size")
    val tick_size:      Double,
)