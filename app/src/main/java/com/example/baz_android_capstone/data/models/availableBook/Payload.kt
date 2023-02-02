package com.example.baz_android_capstone.data.models.availableBook

data class Payload(
    val book: String,
    val default_chart: String,
    val fees: Fees,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String,
    val tick_size: String
)
