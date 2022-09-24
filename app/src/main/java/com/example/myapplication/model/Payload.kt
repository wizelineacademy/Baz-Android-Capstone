package com.example.myapplication.model

data class Payload(
    val book: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)

fun Payload.asExternalModel() = CriptoCurrency(
    name = book, price = maximum_price
)