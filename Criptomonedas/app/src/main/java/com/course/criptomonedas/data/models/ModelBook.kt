package com.course.criptomonedas.data.models

data class ModelBook(
    val book: String,
    val default_chart: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String,
    val tick_size: String
)